package com.flightping.backend.domain.interested.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interested_routes")
@Getter
@NoArgsConstructor
public class InterestedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String departure;  // 출발 공항 코드 (ex. ICN)

    @Column(nullable = false)
    private String dest;  // 도착 공항 코드 (ex. NRT)

}
