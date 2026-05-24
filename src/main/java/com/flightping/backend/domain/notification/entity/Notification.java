package com.flightping.backend.domain.notification.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
