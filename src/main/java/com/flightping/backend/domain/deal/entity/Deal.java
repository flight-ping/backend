package com.flightping.backend.domain.deal.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
