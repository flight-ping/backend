package com.flightping.backend.domain.destination.service;

import com.flightping.backend.domain.airport.entity.AirportType;
import com.flightping.backend.domain.airport.repository.AirportRepository;
import com.flightping.backend.domain.destination.dto.DestinationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DestinationService {

    private final AirportRepository airportRepository;

    public DestinationResponse getDestinations() {
        List<DestinationResponse.DestinationDto> destinations = airportRepository
                .findByTypeOrderByCityAsc(AirportType.DESTINATION)
                .stream()
                .map(DestinationResponse.DestinationDto::from)
                .collect(Collectors.toList());

        return new DestinationResponse(destinations);
    }
}
