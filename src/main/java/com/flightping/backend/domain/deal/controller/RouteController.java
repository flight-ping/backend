package com.flightping.backend.domain.deal.controller;

import com.flightping.backend.domain.deal.dto.RouteDealsResponse;
import com.flightping.backend.domain.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final DealService dealService;

    @GetMapping("/deals")
    public ResponseEntity<RouteDealsResponse> getRouteDeals(
            @RequestParam String departure,
            @RequestParam String dest
    ) {
        return ResponseEntity.ok(dealService.getRouteDeals(departure, dest));
    }
}
