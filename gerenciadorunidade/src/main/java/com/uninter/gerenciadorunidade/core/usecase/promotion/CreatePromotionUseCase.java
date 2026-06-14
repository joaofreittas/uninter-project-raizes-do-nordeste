package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import com.uninter.gerenciadorunidade.core.input.promotion.CreatePromotionInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePromotionUseCase {

    private final PromotionGateway promotionGateway;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public PromotionDomain execute(final CreatePromotionInput input) {
        unitGateway.findById(input.unitId())
            .orElseThrow(() -> new DomainException("Unit not found: " + input.unitId()));

        return promotionGateway.save(
            PromotionDomain.create(input.unitId(), input.title(),
                input.startDate(), input.endDate(), input.reward()));
    }

}
