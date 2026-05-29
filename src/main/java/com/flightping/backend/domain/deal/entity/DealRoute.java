package com.flightping.backend.domain.deal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deal_routes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DealRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id", nullable = false)
    private Deal deal;

    @Column(nullable = false)
    private String routeText;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String tripType;

    public static DealRoute of(Deal deal, String routeText, Integer price, String tripType) {
        return DealRoute.builder()
                .deal(deal)
                .routeText(routeText)
                .price(price)
                .tripType(tripType)
                .build();
    }
}
