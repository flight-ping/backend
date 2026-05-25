package com.flightping.backend.domain.destination.controller;

import com.flightping.backend.domain.destination.dto.DestinationResponse;
import com.flightping.backend.domain.destination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping
    public ResponseEntity<DestinationResponse> getDestinations() {
        return ResponseEntity.ok(destinationService.getDestinations());
    }
}
