package com.flightping.backend.domain.airport.repository;

import com.flightping.backend.domain.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAllByOrderByCityAsc();
}
