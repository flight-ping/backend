package com.flightping.backend.domain.flight.controller;

import com.flightping.backend.domain.flight.dto.FlightResponse;
import com.flightping.backend.domain.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<FlightResponse> getFlights(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam String date
    ) {
        return ResponseEntity.ok(flightService.getFlights(departure, destination, date));
    }
}
