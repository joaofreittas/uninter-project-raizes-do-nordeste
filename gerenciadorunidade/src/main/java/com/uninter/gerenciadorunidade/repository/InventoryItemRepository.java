package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByUnitId(Long unitId);

    List<InventoryItem> findByProductId(Long productId);

}
