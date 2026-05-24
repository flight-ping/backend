package com.flightping.backend.domain.interested.repository;

import com.flightping.backend.domain.interested.entity.InterestedRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestedRouteRepository extends JpaRepository<InterestedRoute, Long> {
}
