package com.flightping.backend.domain.saved.controller;

import com.flightping.backend.domain.saved.dto.SavedDealResponse;
import com.flightping.backend.domain.saved.service.SavedService;
import lombok.RequiredArgsConstructor;
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
}
