package com.uninter.gerenciadorunidade.controller.unit.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUnitRequest(
    @NotBlank String name,
    @NotBlank String address
) {

}
