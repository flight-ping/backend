package com.flightping.backend.domain.deal.controller;

import com.flightping.backend.domain.airport.dto.AirportRouteSeedRequest;
import com.flightping.backend.domain.airport.dto.AirportSeedRequest;
import com.flightping.backend.domain.airport.service.AirportService;
import com.flightping.backend.domain.deal.service.DealSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class DealSyncController {

    private final DealSyncService dealSyncService;
    private final AirportService airportService;

    @PostMapping("/deals/sync")
    public ResponseEntity<DealSyncService.SyncResult> syncDeals() {
        return ResponseEntity.ok(dealSyncService.sync());
    }

    @PostMapping("/airports/seed")
    public ResponseEntity<Map<String, Integer>> seedAirports(@RequestBody List<AirportSeedRequest> airports) {
        return ResponseEntity.ok(airportService.seedAirports(airports));
    }

    @PostMapping("/routes/seed")
    public ResponseEntity<Map<String, Integer>> seedRoutes(@RequestBody List<AirportRouteSeedRequest> routes) {
        return ResponseEntity.ok(airportService.seedRoutes(routes));
    }
}
