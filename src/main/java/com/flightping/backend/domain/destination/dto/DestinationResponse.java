package com.flightping.backend.domain.destination.dto;

import com.flightping.backend.domain.airport.entity.Airport;

import java.util.List;

public record DestinationResponse(
        List<DestinationDto> destinations
) {
    public record DestinationDto(
            String code,
            String city,
            String display
    ) {
        public static DestinationDto from(Airport airport) {
            return new DestinationDto(
                    airport.getCode(),
                    airport.getCity(),
                    airport.getCity() + "(" + airport.getCode() + ")"
            );
        }
    }
}
