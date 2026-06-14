package com.uninter.gerenciadorunidade.entrypoint.api.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateInventoryItemRequest(
    @NotNull Long unitId,
    @NotNull Long productId,
    @NotNull @Min(0) Integer quantity,
    @NotNull @Min(0) Integer minimumQuantity
) {

}
