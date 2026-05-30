package com.flightping.backend.domain.airport.repository;

import com.flightping.backend.domain.airport.entity.AirportRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AirportRouteRepository extends JpaRepository<AirportRoute, Long> {

    @Query("SELECT r.arrivalCode FROM AirportRoute r WHERE r.departureCode = :departureCode")
    List<String> findArrivalCodesByDepartureCode(String departureCode);

    @Query("SELECT DISTINCT r.departureCode FROM AirportRoute r")
    List<String> findAllDepartureCodes();

    Set<AirportRoute> findByDepartureCodeAndArrivalCodeIn(String departureCode, List<String> arrivalCodes);
}
