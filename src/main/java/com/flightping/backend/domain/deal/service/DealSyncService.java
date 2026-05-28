package com.flightping.backend.domain.deal.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightping.backend.domain.airport.entity.Airport;
import com.flightping.backend.domain.airport.repository.AirportRepository;
import com.flightping.backend.domain.airport.service.AirportLookupService;
import com.flightping.backend.domain.deal.entity.CrawlerDealDto;
import com.flightping.backend.domain.deal.entity.Deal;
import com.flightping.backend.domain.deal.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealSyncService {

    private final RestTemplate restTemplate;
    private final DealRepository dealRepository;
    private final AirportRepository airportRepository;
    private final AirportLookupService airportLookupService;

    @Value("${crawler.url}")
    private String crawlerUrl;

    @Scheduled(cron = "0 0 */6 * * *") //스케줄러
    @Transactional
    public SyncResult sync() {
        log.info("크롤러 동기화 시작: {}", crawlerUrl);

        CrawlerResponse response;
        try {
            response = restTemplate.getForObject(crawlerUrl + "/deals", CrawlerResponse.class);
        } catch (Exception e) {
            log.error("크롤러 호출 실패: {}", e.getMessage());
            return new SyncResult(0, 0, e.getMessage());
        }

        if (response == null || response.deals() == null || response.deals().isEmpty()) {
            return new SyncResult(0, 0, null);
        }

        Map<String, Airport> airportMap = airportRepository.findAll().stream()
                .collect(Collectors.toMap(Airport::getCode, a -> a, (a, b) -> a));

        int created = 0, updated = 0;
        for (CrawlerDealDto dto : response.deals()) {
            Airport dep = ensureAirport(dto.departure(), airportMap);
            Airport arr = ensureAirport(dto.dest(), airportMap);

            String depCity = dep != null ? dep.getCity() : dto.departure();
            String arrCity = arr != null ? arr.getCity() : dto.dest();
            String flag    = arr != null ? arr.getFlag() : "";

            var existing = dealRepository.findByBookingUrl(dto.bookingUrl());
            if (existing.isPresent()) {
                existing.get().updateFrom(dto, depCity, arrCity, flag);
                updated++;
            } else {
                dealRepository.save(Deal.from(dto, depCity, arrCity, flag));
                created++;
            }
        }

        log.info("완료 - 신규: {}, 갱신: {}", created, updated);
        return new SyncResult(created, updated, null);
    }


    private Airport ensureAirport(String iata, Map<String, Airport> airportMap) {
        if (airportMap.containsKey(iata)) return airportMap.get(iata);

        AirportLookupService.AirportInfo info = airportLookupService.lookup(iata);
        if (info == null) {
            log.warn("공항 조회 실패: {}", iata);
            return null;
        }

        Airport airport = airportRepository.findByCode(iata)
                .map(existing -> { existing.update(info.city(), info.isoCode()); return existing; })
                .orElse(Airport.builder().code(iata).city(info.city()).isoCode(info.isoCode()).build());
        airport = airportRepository.save(airport);
        airportMap.put(iata, airport);
        log.info("공항 등록: {} -> {} ({})", iata, info.city(), info.isoCode());
        return airport;
    }

    //내부 타입
    private record CrawlerResponse(@JsonProperty("deals") List<CrawlerDealDto> deals) {}
    public record SyncResult(int created, int updated, String error) {}
}
