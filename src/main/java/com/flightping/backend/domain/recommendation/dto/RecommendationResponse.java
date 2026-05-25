package com.flightping.backend.domain.recommendation.dto;

import com.flightping.backend.domain.deal.dto.DealItemDto;

import java.util.List;

public record RecommendationResponse(List<RouteRecommendation> routes) {

    public record RouteRecommendation(
            String departure,
            String dest,
            List<DealItemDto> deals
    ) {}
}
