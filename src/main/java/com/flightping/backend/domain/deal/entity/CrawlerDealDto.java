package com.flightping.backend.domain.deal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record CrawlerDealDto(
        String airline,
        String title,
        String departure,
        String dest,
        Integer price,
        @JsonProperty("sale_start") LocalDate saleStart,
        @JsonProperty("sale_end")   LocalDate saleEnd,
        @JsonProperty("booking_url") String bookingUrl,
        String color,
        @JsonProperty("image_url")  String imageUrl,
        List<RouteDto> routes
) {
    public record RouteDto(
            @JsonProperty("route_text") String routeText,
            Integer price,
            @JsonProperty("trip_type") String tripType,
            @JsonProperty("dep_code") String depCode,
            @JsonProperty("arr_code") String arrCode
    ) {}
}
