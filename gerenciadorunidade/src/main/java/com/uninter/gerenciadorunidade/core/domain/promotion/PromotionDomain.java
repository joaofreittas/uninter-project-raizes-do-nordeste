package com.uninter.gerenciadorunidade.core.domain.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDomain {

    private Long id;
    private Long unitId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PromotionReward reward;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
