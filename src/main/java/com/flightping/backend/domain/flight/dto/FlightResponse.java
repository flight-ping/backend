package com.flightping.backend.domain.flight.dto;

import java.util.List;

public record FlightResponse(
        List<FlightDto> flights
) {
    public record FlightDto(
            Integer price,
            Integer duration,
            Integer stops,
            List<LegDto> legs
    ) {}

    public record LegDto(
            String airline,
            String flightNumber,
            String departure,
            String arrival
    ) {}
}
