package com.uninter.gerenciadorunidade.core.input.promotion;

import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionReward;

import java.time.LocalDateTime;

public record CreatePromotionInput(
    Long unitId,
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    PromotionReward reward
) {

}
