package com.flightping.backend.domain.interested.dto;

import jakarta.validation.constraints.NotBlank;

public record AddInterestedRouteRequest(
        @NotBlank String departure,
        @NotBlank String dest
) {}
