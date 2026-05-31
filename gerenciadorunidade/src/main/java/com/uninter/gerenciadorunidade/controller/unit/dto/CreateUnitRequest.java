package com.uninter.gerenciadorunidade.controller.unit.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUnitRequest(
    @NotBlank String name,
    @NotBlank String address
) {

}
