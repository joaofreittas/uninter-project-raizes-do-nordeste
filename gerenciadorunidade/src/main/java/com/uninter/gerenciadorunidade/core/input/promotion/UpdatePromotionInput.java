package com.uninter.gerenciadorunidade.core.input.promotion;

import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionReward;

import java.time.LocalDateTime;

public record UpdatePromotionInput(
    Long id,
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    PromotionReward reward
) {

}
