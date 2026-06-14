package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.promotion.CustomerPromotionDomain;

import java.util.List;
import java.util.Optional;

public interface CustomerPromotionGateway {

    CustomerPromotionDomain save(CustomerPromotionDomain customerPromotion);

    Optional<CustomerPromotionDomain> findById(Long id);

    List<CustomerPromotionDomain> findByCustomerId(Long customerId);

    List<CustomerPromotionDomain> findByPromotionId(Long promotionId);

    void delete(CustomerPromotionDomain customerPromotion);

}
