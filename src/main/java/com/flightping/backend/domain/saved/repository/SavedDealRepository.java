package com.flightping.backend.domain.saved.repository;

import com.flightping.backend.domain.saved.entity.SavedDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedDealRepository extends JpaRepository<SavedDeal, Long> {

    List<SavedDeal> findByUserId(String userId);
}
