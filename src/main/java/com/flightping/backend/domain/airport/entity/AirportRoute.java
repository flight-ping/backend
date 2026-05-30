package com.flightping.backend.domain.airport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "airport_routes",
    uniqueConstraints = @UniqueConstraint(columnNames = {"departure_code", "arrival_code"})
)
@Getter
@NoArgsConstructor
public class AirportRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_code", nullable = false)
    private String departureCode;

    @Column(name = "arrival_code", nullable = false)
    private String arrivalCode;

    public AirportRoute(String departureCode, String arrivalCode) {
        this.departureCode = departureCode;
        this.arrivalCode = arrivalCode;
    }
}
