package com.flightping.backend.domain.airport.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AirportLookupService {

    private static final String PAGE_URL = "https://www.airportal.go.kr/airport/airport.do";
    private static final String API_URL  = "https://www.airportal.go.kr/airport/searchAirport.do";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public record AirportInfo(String code, String city, String isoCode) {}

    public Optional<AirportInfo> lookup(String iata) {
        try {
            var cookieManager = new CookieManager();
            var client = HttpClient.newBuilder()
                    .cookieHandler(cookieManager)
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(PAGE_URL))
                            .header("User-Agent", "Mozilla/5.0")
                            .GET().build(),
                    HttpResponse.BodyHandlers.discarding()
            );

            String body = objectMapper.writeValueAsString(Map.of(
                    "pageNumber", 1, "pageSize", 50,
                    "order", "asc", "orderNm", "name2",
                    "searchName", iata.toLowerCase()
            ));

            var response = client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(API_URL))
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .header("Content-Type", "application/json;charset=UTF-8")
                            .header("Referer", PAGE_URL)
                            .header("request-call", "web-rest")
                            .header("User-Agent", "Mozilla/5.0")
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) return Optional.empty();

            JsonNode content = objectMapper.readTree(response.body()).path("content");
            if (!content.isArray()) return Optional.empty();

            for (JsonNode item : content) {
                if (iata.equalsIgnoreCase(item.path("iataCode").asText())) {
                    String city    = cleanCity(item.path("koCity").asText(""));
                    String isoCode = item.path("countryCode").asText("").strip().toUpperCase();
                    if (!city.isEmpty() && !isoCode.isEmpty()) {
                        return Optional.of(new AirportInfo(iata, city, isoCode));
                    }
                }
            }
        } catch (Exception e) {
            log.warn("airportal 조회 실패 [{}]: {}", iata, e.getMessage());
        }
        return Optional.empty();
    }

    private String cleanCity(String raw) {
        return raw.replaceAll("[^가-힣\\s/]+$", "").strip();
    }
}
