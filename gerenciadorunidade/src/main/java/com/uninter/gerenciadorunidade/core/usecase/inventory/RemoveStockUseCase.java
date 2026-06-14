package com.uninter.gerenciadorunidade.core.usecase.inventory;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.inventory.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import com.uninter.gerenciadorunidade.core.input.inventory.AdjustStockInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveStockUseCase {

    private final FindInventoryItemByIdUseCase findInventoryItemByIdUseCase;
    private final InventoryItemGateway inventoryItemGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "InventoryItem.removeStock")
    public InventoryItemDomain execute(final AdjustStockInput input) {
        var existing = findInventoryItemByIdUseCase.execute(input.id());
        return inventoryItemGateway.save(existing.removeStock(input.amount()));
    }

}
