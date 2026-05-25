package com.flightping.backend.domain.interested.dto;

import com.flightping.backend.domain.interested.entity.InterestedRoute;

import java.util.List;

public record InterestedRouteResponse(
        List<RouteDto> routes
) {
    public record RouteDto(
            Long id,
            String departure,   // 출발 공항 코드 (ex. ICN)
            String dest         // 도착 공항 코드 (ex. NRT)
    ) {
        public static RouteDto from(InterestedRoute route) {
            return new RouteDto(
                    route.getId(),
                    route.getDeparture(),
                    route.getDest()
            );
        }
    }
}
