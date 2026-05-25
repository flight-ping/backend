package com.flightping.backend.domain.airport.dto;

import com.flightping.backend.domain.airport.entity.Airport;

import java.util.List;

public record AirportResponse(
        List<AirportDto> airports
) {
    public record AirportDto(
            String code,
            String city,
            String display  // "인천(ICN)" 형식
    ) {
        public static AirportDto from(Airport airport) {
            return new AirportDto(
                    airport.getCode(),
                    airport.getCity(),
                    airport.getCity() + "(" + airport.getCode() + ")"
            );
        }
    }
}
