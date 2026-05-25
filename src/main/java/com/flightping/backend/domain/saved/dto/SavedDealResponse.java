package com.flightping.backend.domain.saved.dto;

import com.flightping.backend.domain.deal.dto.DealItemDto;

import java.util.List;

public record SavedDealResponse(List<DealItemDto> deals) {
}
