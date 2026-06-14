package com.uninter.gerenciadorunidade.entrypoint.api.menu.dto;

import jakarta.validation.constraints.NotNull;

public record CreateMenuRequest(@NotNull Long unitId) {}
