package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionDomain;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionReward;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreatePromotionUseCase {

    private final PromotionGateway promotionGateway;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public PromotionDomain execute(final Long unitId, final String title,
                                   final LocalDateTime startDate, final LocalDateTime endDate,
                                   final PromotionReward reward) {
        if (!startDate.isBefore(endDate)) {
            throw new DomainException("startDate deve ser anterior a endDate");
        }

        unitGateway.findById(unitId)
            .orElseThrow(() -> new DomainException("Unit not found: " + unitId));

        var now = LocalDateTime.now();
        var promotion = PromotionDomain.builder()
            .unitId(unitId)
            .title(title)
            .startDate(startDate)
            .endDate(endDate)
            .reward(reward)
            .createdAt(now)
            .build();

        return promotionGateway.save(promotion);
    }

}
