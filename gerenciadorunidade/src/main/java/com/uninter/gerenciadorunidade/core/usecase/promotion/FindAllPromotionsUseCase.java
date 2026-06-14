package com.uninter.gerenciadorunidade.core.usecase.promotion;

import com.uninter.gerenciadorunidade.core.domain.promotion.PromotionDomain;
import com.uninter.gerenciadorunidade.core.gateway.PromotionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllPromotionsUseCase {

    private final PromotionGateway promotionGateway;

    public List<PromotionDomain> execute() {
        return promotionGateway.findAll();
    }

}
