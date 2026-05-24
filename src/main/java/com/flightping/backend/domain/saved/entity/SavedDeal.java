package com.flightping.backend.domain.saved.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SavedDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
