package com.flightping.backend.domain.deal.dto;

import com.flightping.backend.domain.deal.entity.Deal;
import com.flightping.backend.domain.deal.entity.DealRoute;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public record DealDetailResponse(
        Long id,
        String airline,
        String title,
        String departure,
        String dest,
        String isoCode,
        Integer price,
        String priceText,
        LocalDate saleStart,
        LocalDate saleEnd,
        String dday,
        Boolean urgent,
        String color,
        String imageUrl,
        String bookingUrl,
        List<RouteDto> routes
) {
    public record RouteDto(String routeText, Integer price, String tripType) {}

    public static DealDetailResponse from(Deal deal, List<DealRoute> routes) {
        String priceText = "왕복 " + NumberFormat.getNumberInstance(Locale.KOREA).format(deal.getPrice()) + "원~";
        long days = ChronoUnit.DAYS.between(LocalDate.now(), deal.getSaleEnd());
        String dday = days <= 0 ? "D-Day" : "D-" + days;

        List<RouteDto> routeDtos = routes.stream()
                .map(r -> new RouteDto(r.getRouteText(), r.getPrice(), r.getTripType()))
                .toList();

        return new DealDetailResponse(
                deal.getId(),
                deal.getAirline(),
                deal.getTitle(),
                deal.getDeparture(),
                deal.getDest(),
                deal.getIsoCode(),
                deal.getPrice(),
                priceText,
                deal.getSaleStart(),
                deal.getSaleEnd(),
                dday,
                deal.getUrgent(),
                deal.getColor(),
                deal.getImageUrl(),
                deal.getBookingUrl(),
                routeDtos
        );
    }
}
