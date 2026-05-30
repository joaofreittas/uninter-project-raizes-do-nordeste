package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findByUnitId(Long unitId);

}
