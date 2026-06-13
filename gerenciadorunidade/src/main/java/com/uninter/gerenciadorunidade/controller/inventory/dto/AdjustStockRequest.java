package com.uninter.gerenciadorunidade.controller.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AdjustStockRequest(
    @NotNull @Min(1) Integer amount
) {

}
