package com.flightping.backend.domain.deal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "deals")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String dest;

    @Column
    private String isoCode;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate saleStart;

    @Column(nullable = false)
    private LocalDate saleEnd;

    @Column(nullable = false)
    private Boolean urgent;

    @Column(nullable = false)
    private String color;

    @Column
    private String bookingUrl;

    @Column
    private String imageUrl;

    @Column
    private String section;

    @Column
    private String sectionSub;

    /** 크롤러 데이터로부터 신규 Deal 생성 */
    public static Deal from(CrawlerDealDto dto, String depCity, String arrCity, String isoCode) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), dto.saleEnd());
        return Deal.builder()
                .airline(dto.airline())
                .title(dto.title())
                .departure(depCity)
                .dest(arrCity)
                .isoCode(isoCode)
                .price(dto.price())
                .saleStart(dto.saleStart())
                .saleEnd(dto.saleEnd())
                .urgent(daysLeft <= 3)
                .color(dto.color())
                .bookingUrl(dto.bookingUrl())
                .imageUrl(dto.imageUrl())
                .section(dto.airline())
                .sectionSub(null)
                .build();
    }

    /** 크롤러 데이터로 기존 Deal 필드 갱신 */
    public void updateFrom(CrawlerDealDto dto, String depCity, String arrCity, String isoCode) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), dto.saleEnd());
        this.title = dto.title();
        this.departure = depCity;
        this.dest = arrCity;
        this.isoCode = isoCode;
        this.price = dto.price();
        this.saleStart = dto.saleStart();
        this.saleEnd = dto.saleEnd();
        this.urgent = daysLeft <= 3;
        this.color = dto.color();
        this.imageUrl = dto.imageUrl();
        this.section = dto.airline();
    }
}
