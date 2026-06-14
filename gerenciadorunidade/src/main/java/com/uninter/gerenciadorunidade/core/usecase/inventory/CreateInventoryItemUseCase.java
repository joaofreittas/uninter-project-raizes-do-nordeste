package com.uninter.gerenciadorunidade.core.usecase.inventory;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.inventory.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateInventoryItemUseCase {

    private final InventoryItemGateway inventoryItemGateway;
    private final UnitGateway unitGateway;
    private final ProductGateway productGateway;

    @Auditable(action = AuditAction.CREATE)
    public InventoryItemDomain execute(final Long unitId, final Long productId,
                                       final Integer quantity, final Integer minimumQuantity) {
        unitGateway.findById(unitId)
            .orElseThrow(() -> new DomainException("Unit not found: " + unitId));
        productGateway.findById(productId)
            .orElseThrow(() -> new DomainException("Product not found: " + productId));

        var item = InventoryItemDomain.builder()
            .unitId(unitId)
            .productId(productId)
            .quantity(quantity)
            .minimumQuantity(minimumQuantity)
            .createdAt(LocalDateTime.now())
            .build();

        return inventoryItemGateway.save(item);
    }

}
