package com.flightping.backend.domain.deal.repository;

import com.flightping.backend.domain.deal.entity.DealRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRouteRepository extends JpaRepository<DealRoute, Long> {
    void deleteByDealId(Long dealId);
    List<DealRoute> findByDealId(Long dealId);
}
