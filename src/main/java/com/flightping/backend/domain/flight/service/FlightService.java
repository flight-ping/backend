package com.flightping.backend.domain.flight.service;

import com.flightping.backend.domain.flight.dto.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final RestTemplate restTemplate;

    @Value("${crawler.url}")
    private String crawlerUrl;

    public FlightResponse getFlights(String departure, String destination, String date) {
        String url = UriComponentsBuilder
                .fromUriString(crawlerUrl + "/flights")
                .queryParam("departure", departure)
                .queryParam("destination", destination)
                .queryParam("date", date)
                .toUriString();

        try {
            FlightResponse response = restTemplate.getForObject(url, FlightResponse.class);
            return response != null ? response : new FlightResponse(java.util.List.of());
        } catch (Exception e) {
            return new FlightResponse(java.util.List.of());
        }
    }
}
