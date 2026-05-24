package com.flightping.backend.domain.deal.dto;

import com.flightping.backend.domain.deal.entity.Deal;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public record DealItemDto(
        Long id,
        String airline,
        String title,
        String dest,
        Integer price,
        String priceText,
        String dday,
        Boolean urgent,
        String color,
        String flag
) {
    public static DealItemDto from(Deal deal) {
        String priceText = "왕복 " + NumberFormat.getNumberInstance(Locale.KOREA).format(deal.getPrice()) + "원~";
        long days = ChronoUnit.DAYS.between(LocalDate.now(), deal.getSaleEnd());
        String dday = days <= 0 ? "D-Day" : "D-" + days;

        return new DealItemDto(
                deal.getId(),
                deal.getAirline(),
                deal.getTitle(),
                deal.getDest(),
                deal.getPrice(),
                priceText,
                dday,
                deal.getUrgent(),
                deal.getColor(),
                deal.getFlag()
        );
    }
}
