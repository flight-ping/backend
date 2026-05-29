package com.flightping.backend.domain.deal.service;

import com.flightping.backend.common.exception.BusinessException;
import com.flightping.backend.common.exception.ErrorCode;
import com.flightping.backend.domain.deal.dto.DealDetailResponse;
import com.flightping.backend.domain.deal.dto.DealItemDto;
import com.flightping.backend.domain.deal.dto.DealSectionResponse;
import com.flightping.backend.domain.deal.dto.RouteDealsResponse;
import com.flightping.backend.domain.deal.entity.Deal;
import com.flightping.backend.domain.deal.entity.DealRoute;
import com.flightping.backend.domain.deal.repository.DealRepository;
import com.flightping.backend.domain.deal.repository.DealRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealService {

    private final DealRepository dealRepository;
    private final DealRouteRepository dealRouteRepository;

    public DealSectionResponse getDeals() {
        List<Deal> deals = dealRepository.findAllByOrderBySaleEndAsc();

        // section 기준으로 순서 유지하며 그룹핑
        Map<String, List<Deal>> grouped = new LinkedHashMap<>();
        for (Deal deal : deals) {
            String key = deal.getSection() != null ? deal.getSection() : "기타";
            grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(deal);
        }

        List<DealSectionResponse.Section> sections = grouped.entrySet().stream()
                .map(entry -> {
                    List<Deal> sectionDeals = entry.getValue();
                    String sectionSub = sectionDeals.stream()
                            .map(Deal::getSectionSub)
                            .filter(s -> s != null && !s.isBlank())
                            .findFirst()
                            .orElse(null);
                    List<DealItemDto> items = sectionDeals.stream()
                            .map(DealItemDto::from)
                            .collect(Collectors.toList());
                    return new DealSectionResponse.Section(
                            entry.getKey().toLowerCase().replace(" ", "-"),
                            entry.getKey(),
                            sectionSub,
                            items
                    );
                })
                .collect(Collectors.toList());

        return new DealSectionResponse(sections);
    }

    public DealDetailResponse getDealDetail(Long dealId) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new BusinessException(ErrorCode.DEAL_NOT_FOUND));
        List<DealRoute> routes = dealRouteRepository.findByDealId(dealId);
        return DealDetailResponse.from(deal, routes);
    }

    public RouteDealsResponse getRouteDeals(String departure, String dest) {
        List<DealItemDto> deals = dealRepository
                .findByDepartureAndDestOrderBySaleEndAsc(departure, dest)
                .stream()
                .map(DealItemDto::from)
                .collect(Collectors.toList());
        return new RouteDealsResponse(departure, dest, deals);
    }
}
