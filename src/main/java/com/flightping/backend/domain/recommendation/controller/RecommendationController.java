package com.flightping.backend.domain.recommendation.controller;

import com.flightping.backend.domain.recommendation.dto.RecommendationResponse;
import com.flightping.backend.domain.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<RecommendationResponse> getRecommendations(
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(recommendationService.getRecommendations(userId));
    }
}
