package com.flightping.backend.domain.recommendation.service;

import com.flightping.backend.domain.deal.dto.DealItemDto;
import com.flightping.backend.domain.deal.repository.DealRepository;
import com.flightping.backend.domain.interested.entity.InterestedRoute;
import com.flightping.backend.domain.interested.repository.InterestedRouteRepository;
import com.flightping.backend.domain.recommendation.dto.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService {

    private final InterestedRouteRepository interestedRouteRepository;
    private final DealRepository dealRepository;

    public RecommendationResponse getRecommendations(String userId) {
        List<InterestedRoute> interestedRoutes = interestedRouteRepository.findByUserId(userId);

        List<RecommendationResponse.RouteRecommendation> routes = interestedRoutes.stream()
                .map(route -> {
                    List<DealItemDto> deals = dealRepository
                            .findByDepartureAndDestOrderBySaleEndAsc(route.getDeparture(), route.getDest())
                            .stream()
                            .map(DealItemDto::from)
                            .toList();

                    return new RecommendationResponse.RouteRecommendation(
                            route.getDeparture(),
                            route.getDest(),
                            deals
                    );
                })
                .filter(route -> !route.deals().isEmpty())  // 특가 없는 노선 제외
                .toList();

        return new RecommendationResponse(routes);
    }
}
