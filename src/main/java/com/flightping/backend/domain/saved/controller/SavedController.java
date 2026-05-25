package com.flightping.backend.domain.saved.controller;

import com.flightping.backend.domain.saved.dto.SavedDealResponse;
import com.flightping.backend.domain.saved.dto.SavedStatusResponse;
import com.flightping.backend.domain.saved.service.SavedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/saved")
@RequiredArgsConstructor
public class SavedController {

    private final SavedService savedService;

    @GetMapping
    public ResponseEntity<SavedDealResponse> getSavedDeals(
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(savedService.getSavedDeals(userId));
    }

    @PostMapping("/{dealId}")
    public ResponseEntity<Void> saveDeal(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long dealId
    ) {
        savedService.saveDeal(userId, dealId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{dealId}")
    public ResponseEntity<Void> deleteSavedDeal(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long dealId
    ) {
        savedService.deleteSavedDeal(userId, dealId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dealId}/status")
    public ResponseEntity<SavedStatusResponse> getSavedStatus(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long dealId
    ) {
        return ResponseEntity.ok(savedService.getSavedStatus(userId, dealId));
    }
}
