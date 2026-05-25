package com.flightping.backend.domain.deal.dto;

import java.util.List;

public record RouteDealsResponse(
        String departure,
        String dest,
        List<DealItemDto> deals
) {}
