package com.uninter.gerenciadorunidade.core.usecase.inventory;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.inventory.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddStockUseCase {

    private final FindInventoryItemByIdUseCase findInventoryItemByIdUseCase;
    private final InventoryItemGateway inventoryItemGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "InventoryItem.addStock")
    public InventoryItemDomain execute(final Long id, final Integer amount) {
        var existing = findInventoryItemByIdUseCase.execute(id);
        var updated = InventoryItemDomain.builder()
            .id(existing.getId())
            .unitId(existing.getUnitId())
            .productId(existing.getProductId())
            .quantity(existing.getQuantity() + amount)
            .minimumQuantity(existing.getMinimumQuantity())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();
        return inventoryItemGateway.save(updated);
    }

}
