package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionDomain;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionReward;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdatePromotionUseCase {

    private final FindPromotionByIdUseCase findPromotionByIdUseCase;
    private final PromotionGateway promotionGateway;

    @Auditable(action = AuditAction.UPDATE)
    public PromotionDomain execute(final Long id, final String title,
                                   final LocalDateTime startDate, final LocalDateTime endDate,
                                   final PromotionReward reward) {
        if (!startDate.isBefore(endDate)) {
            throw new DomainException("startDate deve ser anterior a endDate");
        }

        var existing = findPromotionByIdUseCase.execute(id);
        var updated = PromotionDomain.builder()
            .id(existing.getId())
            .unitId(existing.getUnitId())
            .title(title)
            .startDate(startDate)
            .endDate(endDate)
            .reward(reward)
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return promotionGateway.save(updated);
    }

}
