package com.flightping.backend.domain.deal.repository;

import com.flightping.backend.domain.deal.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findAllByOrderBySaleEndAsc();

    List<Deal> findByDepartureAndDestOrderBySaleEndAsc(String departure, String dest);

    Optional<Deal> findByBookingUrl(String bookingUrl);
}
