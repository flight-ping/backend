package com.flightping.backend.domain.interested.controller;

import com.flightping.backend.domain.interested.dto.AddInterestedRouteRequest;
import com.flightping.backend.domain.interested.dto.InterestedRouteCheckResponse;
import com.flightping.backend.domain.interested.dto.InterestedRouteResponse;
import com.flightping.backend.domain.interested.service.InterestedRouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<InterestedRouteResponse.RouteDto> addInterestedRoute(
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody AddInterestedRouteRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(interestedRouteService.addInterestedRoute(userId, request));
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<Void> deleteInterestedRoute(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long routeId
    ) {
        interestedRouteService.deleteInterestedRoute(userId, routeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<InterestedRouteCheckResponse> checkInterestedRoute(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam String departure,
            @RequestParam String dest
    ) {
        return ResponseEntity.ok(interestedRouteService.checkInterestedRoute(userId, departure, dest));
    }
}
