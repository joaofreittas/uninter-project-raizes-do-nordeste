package com.uninter.gerenciadorunidade.core.usecase.inventory;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RemoveStockUseCase {

    private final FindInventoryItemByIdUseCase findInventoryItemByIdUseCase;
    private final InventoryItemGateway inventoryItemGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "InventoryItem.removeStock")
    public InventoryItemDomain execute(final Long id, final Integer amount) {
        var existing = findInventoryItemByIdUseCase.execute(id);
        var newQuantity = existing.getQuantity() - amount;

        if (newQuantity < 0) {
            throw new DomainException(
                "Insufficient stock. Current: " + existing.getQuantity() + ", requested: " + amount);
        }

        var updated = InventoryItemDomain.builder()
            .id(existing.getId())
            .unitId(existing.getUnitId())
            .productId(existing.getProductId())
            .quantity(newQuantity)
            .minimumQuantity(existing.getMinimumQuantity())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();
        return inventoryItemGateway.save(updated);
    }

}
