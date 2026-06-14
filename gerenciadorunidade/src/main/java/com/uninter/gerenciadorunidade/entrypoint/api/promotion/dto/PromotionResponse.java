package com.uninter.gerenciadorunidade.entrypoint.api.promotion.dto;

import com.uninter.gerenciadorunidade.core.domain.PromotionDomain;
import com.uninter.gerenciadorunidade.core.domain.PromotionReward;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PromotionResponse(
    Long id,
    Long unitId,
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    PromotionReward reward,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static PromotionResponse fromDomain(final PromotionDomain domain) {
        return PromotionResponse.builder()
            .id(domain.getId())
            .unitId(domain.getUnitId())
            .title(domain.getTitle())
            .startDate(domain.getStartDate())
            .endDate(domain.getEndDate())
            .reward(domain.getReward())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }

}
