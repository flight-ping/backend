package com.flightping.backend.domain.deal.repository;

import com.flightping.backend.domain.deal.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findAllByOrderBySaleEndAsc();

}
