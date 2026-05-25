package com.flightping.backend.domain.saved.service;

import com.flightping.backend.domain.deal.dto.DealItemDto;
import com.flightping.backend.domain.deal.entity.Deal;
import com.flightping.backend.domain.deal.repository.DealRepository;
import com.flightping.backend.domain.saved.dto.SavedDealResponse;
import com.flightping.backend.domain.saved.repository.SavedDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavedService {

    private final SavedDealRepository savedDealRepository;
    private final DealRepository dealRepository;

    public SavedDealResponse getSavedDeals(String userId) {
        List<Long> dealIds = savedDealRepository.findByUserId(userId)
                .stream()
                .map(saved -> saved.getDealId())
                .toList();

        List<DealItemDto> deals = dealRepository.findAllById(dealIds)
                .stream()
                .map(DealItemDto::from)
                .toList();

        return new SavedDealResponse(deals);
    }
}
