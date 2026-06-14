package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePromotionUseCase {

    private final FindPromotionByIdUseCase findPromotionByIdUseCase;
    private final PromotionGateway promotionGateway;

    @Auditable(action = AuditAction.DELETE)
    public void execute(final Long id) {
        var promotion = findPromotionByIdUseCase.execute(id);
        promotionGateway.delete(promotion);
    }

}
