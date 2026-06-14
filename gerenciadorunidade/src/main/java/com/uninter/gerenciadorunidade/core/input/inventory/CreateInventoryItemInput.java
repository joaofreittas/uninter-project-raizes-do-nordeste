package com.uninter.gerenciadorunidade.core.input.inventory;

public record CreateInventoryItemInput(
    Long unitId,
    Long productId,
    Integer quantity,
    Integer minimumQuantity
) {

}
