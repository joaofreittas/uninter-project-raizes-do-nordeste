package com.uninter.gerenciadorunidade.core.usecase.inventory;

import com.uninter.gerenciadorunidade.core.domain.inventory.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.exception.ResourceNotFoundException;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindInventoryItemByIdUseCase {

    private final InventoryItemGateway inventoryItemGateway;

    public InventoryItemDomain execute(final Long id) {
        return inventoryItemGateway.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found: " + id));
    }

}
