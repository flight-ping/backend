package com.flightping.backend.domain.interested.service;

import com.flightping.backend.domain.interested.dto.InterestedRouteResponse;
import com.flightping.backend.domain.interested.repository.InterestedRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestedRouteService {

    private final InterestedRouteRepository interestedRouteRepository;

    public InterestedRouteResponse getInterestedRoutes(String userId) {
        List<InterestedRouteResponse.RouteDto> routes = interestedRouteRepository
                .findByUserId(userId)
                .stream()
                .map(InterestedRouteResponse.RouteDto::from)
                .collect(Collectors.toList());

        return new InterestedRouteResponse(routes);
    }
}
