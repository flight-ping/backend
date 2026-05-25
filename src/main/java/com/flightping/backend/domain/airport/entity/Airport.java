package com.flightping.backend.domain.airport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airports")
@Getter
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;  // 공항 코드 (e.g. ICN, GMP)

    @Column(nullable = false)
    private String city;  // 도시명 (e.g. 인천, 김포)
}
