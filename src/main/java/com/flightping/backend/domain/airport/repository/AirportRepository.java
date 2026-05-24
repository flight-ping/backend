package com.flightping.backend.domain.airport.repository;

import com.flightping.backend.domain.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
