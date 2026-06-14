package com.uninter.gerenciadorunidade.dataprovider.database.promotion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionJpaRepository extends JpaRepository<PromotionEntity, Long> {

    List<PromotionEntity> findByUnitId(Long unitId);

}
