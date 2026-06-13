package com.uninter.gerenciadorunidade.controller.inventory.dto;

import com.uninter.gerenciadorunidade.model.unit.InventoryItem;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InventoryItemResponse(
    Long id,
    Long unitId,
    Long productId,
    Integer quantity,
    Integer minimumQuantity,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static InventoryItemResponse fromModel(final InventoryItem item) {
        return InventoryItemResponse.builder()
            .id(item.getId())
            .unitId(item.getUnit().getId())
            .productId(item.getProduct().getId())
            .quantity(item.getQuantity())
            .minimumQuantity(item.getMinimumQuantity())
            .createdAt(item.getCreatedAt())
            .updatedAt(item.getUpdatedAt())
            .build();
    }

}
