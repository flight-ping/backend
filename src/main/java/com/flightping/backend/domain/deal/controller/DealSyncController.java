package com.flightping.backend.domain.deal.controller;

import com.flightping.backend.domain.deal.service.DealSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class DealSyncController {

    private final DealSyncService dealSyncService;

    /**
     * POST /api/internal/deals/sync
     */
    @PostMapping("/deals/sync")
    public ResponseEntity<DealSyncService.SyncResult> syncDeals() {
        return ResponseEntity.ok(dealSyncService.sync());
    }
}
