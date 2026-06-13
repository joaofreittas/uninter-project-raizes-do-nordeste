package com.uninter.gerenciadorunidade.controller.promotion.dto;

import com.uninter.gerenciadorunidade.model.unit.Promotion;
import com.uninter.gerenciadorunidade.model.unit.PromotionReward;
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

    public static PromotionResponse fromModel(final Promotion promotion) {
        return PromotionResponse.builder()
            .id(promotion.getId())
            .unitId(promotion.getUnit().getId())
            .title(promotion.getTitle())
            .startDate(promotion.getStartDate())
            .endDate(promotion.getEndDate())
            .reward(promotion.getReward())
            .createdAt(promotion.getCreatedAt())
            .updatedAt(promotion.getUpdatedAt())
            .build();
    }

}
