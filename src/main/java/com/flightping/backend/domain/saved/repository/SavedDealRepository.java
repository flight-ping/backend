package com.flightping.backend.domain.saved.repository;

import com.flightping.backend.domain.saved.entity.SavedDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedDealRepository extends JpaRepository<SavedDeal, Long> {

    List<SavedDeal> findByUserId(String userId);

    boolean existsByUserIdAndDealId(String userId, Long dealId);

    Optional<SavedDeal> findByUserIdAndDealId(String userId, Long dealId);
}
