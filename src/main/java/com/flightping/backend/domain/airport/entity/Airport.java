package com.flightping.backend.domain.airport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airports")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String city;

    @Column
    private String isoCode;

    public void update(String city, String isoCode) {
        this.city = city;
        this.isoCode = isoCode;
    }

    public String getFlag() {
        if (isoCode == null || isoCode.length() != 2) return "";
        int a = 0x1F1E6 + (isoCode.charAt(0) - 'A');
        int b = 0x1F1E6 + (isoCode.charAt(1) - 'A');
        return new String(Character.toChars(a)) + new String(Character.toChars(b));
    }
}
