package com.flightping.backend.domain.deal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

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
        @JsonProperty("image_url")  String imageUrl
) {}
