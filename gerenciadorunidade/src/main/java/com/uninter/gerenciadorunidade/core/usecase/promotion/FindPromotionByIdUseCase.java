package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.PromotionDomain;
import com.uninter.gerenciadorunidade.core.exception.ResourceNotFoundException;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPromotionByIdUseCase {

    private final PromotionGateway promotionGateway;

    public PromotionDomain execute(final Long id) {
        return promotionGateway.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found: " + id));
    }

}
