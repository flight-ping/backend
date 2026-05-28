package com.flightping.backend.domain.airport.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportLookupService {

    private static final String AIRPORTAL_PAGE = "https://www.airportal.go.kr/airport/airport.do";
    private static final String AIRPORTAL_API  = "https://www.airportal.go.kr/airport/searchAirport.do";

    private final ObjectMapper objectMapper;

    public record AirportInfo(String code, String city, String isoCode) {}

    public AirportInfo lookup(String iata) {
        try {
            CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            HttpClient client = HttpClient.newBuilder()
                    .cookieHandler(cookieManager)
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            client.send(
                    HttpRequest.newBuilder(URI.create(AIRPORTAL_PAGE)).GET().build(),
                    HttpResponse.BodyHandlers.discarding()
            );

            String formBody = "pageNumber=1&pageSize=50&order=asc&orderNm=name2&searchName=" + iata.toLowerCase();
            HttpResponse<String> resp = client.send(
                    HttpRequest.newBuilder(URI.create(AIRPORTAL_API))
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .header("Referer", AIRPORTAL_PAGE)
                            .POST(HttpRequest.BodyPublishers.ofString(formBody))
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode content = objectMapper.readTree(resp.body()).get("content");
            if (content == null || !content.isArray()) return null;

            for (JsonNode item : content) {
                if (!iata.equalsIgnoreCase(item.path("iataCode").asText("").strip())) continue;

                String rawCity = item.path("koCity").asText("").strip();
                String city = rawCity.replaceAll("[^가-힣\\s/]+$", "").strip();
                if (city.isEmpty()) city = rawCity;

                String iso = item.path("countryCode").asText("").strip();
                if (city.isEmpty() || iso.isEmpty()) return null;

                return new AirportInfo(iata, city, iso);
            }
        } catch (Exception e) {
            log.warn("[AirportLookup] {} 조회 실패: {}", iata, e.getMessage());
        }
        return null;
    }
}
