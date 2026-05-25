package com.flightping.backend.domain.airport.controller;

import com.flightping.backend.domain.airport.dto.AirportResponse;
import com.flightping.backend.domain.airport.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<AirportResponse> getAirports() {
        return ResponseEntity.ok(airportService.getAirports());
    }
}
