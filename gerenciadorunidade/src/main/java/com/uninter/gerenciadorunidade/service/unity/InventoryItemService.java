package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.controller.inventory.dto.AdjustStockRequest;
import com.uninter.gerenciadorunidade.controller.inventory.dto.CreateInventoryItemRequest;
import com.uninter.gerenciadorunidade.controller.inventory.dto.UpdateInventoryItemRequest;
import com.uninter.gerenciadorunidade.model.unit.InventoryItem;
import com.uninter.gerenciadorunidade.repository.InventoryItemRepository;
import com.uninter.gerenciadorunidade.repository.ProductRepository;
import com.uninter.gerenciadorunidade.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final UnitRepository unitRepository;
    private final ProductRepository productRepository;

    @Auditable(action = AuditAction.CREATE)
    public InventoryItem create(final CreateInventoryItemRequest request) {
        var unit = unitRepository.findById(request.unitId())
            .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + request.unitId()));

        var product = productRepository.findById(request.productId())
            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + request.productId()));

        var now = LocalDateTime.now();
        var item = InventoryItem.builder()
            .unit(unit)
            .product(product)
            .quantity(request.quantity())
            .minimumQuantity(request.minimumQuantity())
            .createdAt(now)
            .build();

        return inventoryItemRepository.save(item);
    }

    public List<InventoryItem> findAll() {
        return inventoryItemRepository.findAll();
    }

    public List<InventoryItem> findByUnitId(final Long unitId) {
        return inventoryItemRepository.findByUnitId(unitId);
    }

    public List<InventoryItem> findByProductId(final Long productId) {
        return inventoryItemRepository.findByProductId(productId);
    }

    public InventoryItem findById(final Long id) {
        return inventoryItemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("InventoryItem not found: " + id));
    }

    @Auditable(action = AuditAction.UPDATE)
    public InventoryItem update(final Long id, final UpdateInventoryItemRequest request) {
        var existing = findById(id);

        var updated = InventoryItem.builder()
            .id(existing.getId())
            .unit(existing.getUnit())
            .product(existing.getProduct())
            .quantity(request.quantity())
            .minimumQuantity(request.minimumQuantity())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return inventoryItemRepository.save(updated);
    }

    @Auditable(action = AuditAction.UPDATE, entity = "InventoryItem.addStock")
    public InventoryItem addStock(final Long id, final AdjustStockRequest request) {
        var existing = findById(id);

        var updated = InventoryItem.builder()
            .id(existing.getId())
            .unit(existing.getUnit())
            .product(existing.getProduct())
            .quantity(existing.getQuantity() + request.amount())
            .minimumQuantity(existing.getMinimumQuantity())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return inventoryItemRepository.save(updated);
    }

    @Auditable(action = AuditAction.UPDATE, entity = "InventoryItem.removeStock")
    public InventoryItem removeStock(final Long id, final AdjustStockRequest request) {
        var existing = findById(id);
        var newQuantity = existing.getQuantity() - request.amount();

        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                "Insufficient stock. Current: " + existing.getQuantity() + ", requested: " + request.amount());
        }

        var updated = InventoryItem.builder()
            .id(existing.getId())
            .unit(existing.getUnit())
            .product(existing.getProduct())
            .quantity(newQuantity)
            .minimumQuantity(existing.getMinimumQuantity())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return inventoryItemRepository.save(updated);
    }

}
