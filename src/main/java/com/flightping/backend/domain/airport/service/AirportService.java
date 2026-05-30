package com.flightping.backend.domain.airport.service;

import com.flightping.backend.domain.airport.dto.AirportResponse;
import com.flightping.backend.domain.airport.dto.AirportRouteSeedRequest;
import com.flightping.backend.domain.airport.dto.AirportSeedRequest;
import com.flightping.backend.domain.airport.entity.Airport;
import com.flightping.backend.domain.airport.entity.AirportRoute;
import com.flightping.backend.domain.airport.repository.AirportRepository;
import com.flightping.backend.domain.airport.repository.AirportRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportRouteRepository airportRouteRepository;

    public AirportResponse getAirports() {
        List<AirportResponse.AirportDto> airports = airportRepository.findAllByOrderByCityAsc().stream()
                .map(AirportResponse.AirportDto::from)
                .collect(Collectors.toList());
        return new AirportResponse(airports);
    }

    public AirportResponse getDepartureAirports() {
        List<String> departureCodes = airportRouteRepository.findAllDepartureCodes();
        List<AirportResponse.AirportDto> airports = airportRepository
                .findAllByCodeInOrderByCityAsc(departureCodes).stream()
                .map(AirportResponse.AirportDto::from)
                .collect(Collectors.toList());
        return new AirportResponse(airports);
    }

    public AirportResponse getDestinations(String departureCode) {
        List<String> arrivalCodes = airportRouteRepository.findArrivalCodesByDepartureCode(departureCode);
        List<AirportResponse.AirportDto> airports = airportRepository
                .findAllByCodeInOrderByCityAsc(arrivalCodes).stream()
                .map(AirportResponse.AirportDto::from)
                .collect(Collectors.toList());
        return new AirportResponse(airports);
    }

    @Transactional
    public Map<String, Integer> seedAirports(List<AirportSeedRequest> requests) {
        int created = 0, updated = 0;
        for (AirportSeedRequest req : requests) {
            var existing = airportRepository.findByCode(req.code());
            if (existing.isPresent()) {
                existing.get().update(req.city(), req.isoCode(), req.countryName(), req.continent());
                updated++;
            } else {
                airportRepository.save(Airport.builder()
                        .code(req.code()).city(req.city()).isoCode(req.isoCode()).countryName(req.countryName()).continent(req.continent()).build());
                created++;
            }
        }
        return Map.of("created", created, "updated", updated);
    }

    @Transactional
    public Map<String, Integer> seedRoutes(List<AirportRouteSeedRequest> requests) {
        // 출발-도착 코드 쌍으로 존재 여부 확인 후 신규 코드만 저장
        Set<String> existingKeys = airportRouteRepository.findAll().stream()
                .map(r -> r.getDepartureCode() + ":" + r.getArrivalCode())
                .collect(Collectors.toSet());

        List<AirportRoute> toSave = requests.stream()
                .filter(req -> !existingKeys.contains(req.departureCode() + ":" + req.arrivalCode()))
                .map(req -> new AirportRoute(req.departureCode(), req.arrivalCode()))
                .collect(Collectors.toList());

        airportRouteRepository.saveAll(toSave);
        return Map.of("created", toSave.size(), "skipped", requests.size() - toSave.size());
    }
}
