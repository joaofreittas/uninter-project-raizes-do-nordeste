package com.uninter.gerenciadorunidade.controller.unit.dto;

import com.uninter.gerenciadorunidade.model.unit.Unit;
import com.uninter.gerenciadorunidade.model.unit.UnitStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UnitResponse(
    Long id,
    String name,
    String address,
    UnitStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static UnitResponse fromModel(final Unit unit) {
        return UnitResponse.builder()
            .id(unit.getId())
            .name(unit.getName())
            .address(unit.getAddress())
            .status(unit.getStatus())
            .createdAt(unit.getCreatedAt())
            .updatedAt(unit.getUpdatedAt())
            .build();
    }

}
