package com.flightping.backend.domain.saved.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saved_deals")
@Getter
@NoArgsConstructor
public class SavedDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Long dealId;

    public SavedDeal(String userId, Long dealId) {
        this.userId = userId;
        this.dealId = dealId;
    }
}
