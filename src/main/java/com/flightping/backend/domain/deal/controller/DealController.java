package com.flightping.backend.domain.deal.controller;

import com.flightping.backend.domain.deal.dto.DealDetailResponse;
import com.flightping.backend.domain.deal.dto.DealSectionResponse;
import com.flightping.backend.domain.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping
    public ResponseEntity<DealSectionResponse> getDeals() {
        return ResponseEntity.ok(dealService.getDeals());
    }

    @GetMapping("/{dealId}")
    public ResponseEntity<DealDetailResponse> getDealDetail(@PathVariable Long dealId) {
        return ResponseEntity.ok(dealService.getDealDetail(dealId));
    }
}
