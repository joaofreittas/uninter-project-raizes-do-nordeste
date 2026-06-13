package com.uninter.gerenciadorunidade.controller.promotion.dto;

import com.uninter.gerenciadorunidade.model.unit.PromotionReward;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreatePromotionRequest(
    @NotNull Long unitId,
    @NotBlank String title,
    @NotNull LocalDateTime startDate,
    @NotNull LocalDateTime endDate,
    @NotNull PromotionReward reward
) {

}
