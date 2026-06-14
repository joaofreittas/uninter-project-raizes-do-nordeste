package com.uninter.gerenciadorunidade.dataprovider.database.promotion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPromotionRepository extends JpaRepository<CustomerPromotionEntity, Long> {

    List<CustomerPromotionEntity> findByCustomerId(Long customerId);

    List<CustomerPromotionEntity> findByPromotionId(Long promotionId);

}
