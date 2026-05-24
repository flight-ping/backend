package com.flightping.backend.domain.interested.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class InterestedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
