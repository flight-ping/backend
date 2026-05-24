package com.flightping.backend.domain.deal.dto;

import java.util.List;

public record DealSectionResponse(
        List<Section> sections
) {
    public record Section(
            String id,
            String section,
            String sectionSub,
            List<DealItemDto> items
    ) {}
}
