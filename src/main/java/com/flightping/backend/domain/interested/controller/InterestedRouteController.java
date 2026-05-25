package com.flightping.backend.domain.interested.controller;

import com.flightping.backend.domain.interested.dto.InterestedRouteResponse;
import com.flightping.backend.domain.interested.service.InterestedRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interested-routes")
@RequiredArgsConstructor
public class InterestedRouteController {

    private final InterestedRouteService interestedRouteService;

    @GetMapping
    public ResponseEntity<InterestedRouteResponse> getInterestedRoutes(
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(interestedRouteService.getInterestedRoutes(userId));
    }
}
