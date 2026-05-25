package com.flightping.backend.domain.interested.repository;

import com.flightping.backend.domain.interested.entity.InterestedRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestedRouteRepository extends JpaRepository<InterestedRoute, Long> {

    List<InterestedRoute> findByUserId(String userId);
}
