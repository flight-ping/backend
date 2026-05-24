package com.flightping.backend.domain.airport.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
