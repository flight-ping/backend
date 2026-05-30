package com.flightping.backend.domain.deal.repository;

import com.flightping.backend.domain.deal.entity.Deal;
import com.flightping.backend.domain.deal.entity.DealRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DealRouteRepository extends JpaRepository<DealRoute, Long> {
    void deleteByDealId(Long dealId);
    List<DealRoute> findByDealId(Long dealId);

    @Query("SELECT DISTINCT r.deal FROM DealRoute r WHERE r.depCode = :depCode AND r.arrCode = :arrCode AND r.deal.saleEnd >= :today ORDER BY r.deal.saleEnd ASC")
    List<Deal> findActiveDealsByRouteCodes(@Param("depCode") String depCode, @Param("arrCode") String arrCode, @Param("today") LocalDate today);
}
