package com.uninter.gerenciadorunidade.controller.menu.dto;

import jakarta.validation.constraints.NotNull;

public record CreateMenuRequest(
    @NotNull Long unitId
) {

}
