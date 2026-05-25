package com.flightping.backend.domain.interested.service;

import com.flightping.backend.common.exception.BusinessException;
import com.flightping.backend.common.exception.ErrorCode;
import com.flightping.backend.domain.interested.dto.AddInterestedRouteRequest;
import com.flightping.backend.domain.interested.dto.InterestedRouteResponse;
import com.flightping.backend.domain.interested.entity.InterestedRoute;
import com.flightping.backend.domain.interested.repository.InterestedRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestedRouteService {

    private final InterestedRouteRepository interestedRouteRepository;

    @Transactional(readOnly = true)
    public InterestedRouteResponse getInterestedRoutes(String userId) {
        List<InterestedRouteResponse.RouteDto> routes = interestedRouteRepository
                .findByUserId(userId)
                .stream()
                .map(InterestedRouteResponse.RouteDto::from)
                .collect(Collectors.toList());

        return new InterestedRouteResponse(routes);
    }

    @Transactional
    public InterestedRouteResponse.RouteDto addInterestedRoute(String userId, AddInterestedRouteRequest request) {
        if (interestedRouteRepository.existsByUserIdAndDepartureAndDest(userId, request.departure(), request.dest())) {
            throw new BusinessException(ErrorCode.ROUTE_ALREADY_EXISTS);
        }

        InterestedRoute route = new InterestedRoute(userId, request.departure(), request.dest());
        InterestedRoute saved = interestedRouteRepository.save(route);
        return InterestedRouteResponse.RouteDto.from(saved);
    }
}
