package com.flightping.backend.domain.deal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "deals")
@Getter
@NoArgsConstructor
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

    @Column(nullable = false)
    private String flag;

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
}
