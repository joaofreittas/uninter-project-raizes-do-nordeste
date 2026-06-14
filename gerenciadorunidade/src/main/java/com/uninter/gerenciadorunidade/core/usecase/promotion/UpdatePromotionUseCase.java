package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionDomain;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionReward;
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
        var existing = findPromotionByIdUseCase.execute(id);
        return promotionGateway.save(existing.update(title, startDate, endDate, reward));
    }

}
