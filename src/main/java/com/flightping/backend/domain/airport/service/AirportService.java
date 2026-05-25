package com.flightping.backend.domain.airport.service;

import com.flightping.backend.domain.airport.dto.AirportResponse;
import com.flightping.backend.domain.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportResponse getAirports() {
        List<AirportResponse.AirportDto> airports = airportRepository.findAllByOrderByCityAsc().stream()
                .map(AirportResponse.AirportDto::from)
                .collect(Collectors.toList());
        return new AirportResponse(airports);
    }
}
