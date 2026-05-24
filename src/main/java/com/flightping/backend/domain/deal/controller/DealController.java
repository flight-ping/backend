package com.flightping.backend.domain.deal.controller;

import com.flightping.backend.domain.deal.dto.DealSectionResponse;
import com.flightping.backend.domain.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping
    public ResponseEntity<DealSectionResponse> getDeals() {
        return ResponseEntity.ok(dealService.getDeals());
    }
}
