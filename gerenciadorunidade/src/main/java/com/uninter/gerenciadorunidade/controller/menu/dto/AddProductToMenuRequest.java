package com.uninter.gerenciadorunidade.controller.menu.dto;

import jakarta.validation.constraints.NotNull;

public record AddProductToMenuRequest(
    @NotNull Long productId
) {

}
