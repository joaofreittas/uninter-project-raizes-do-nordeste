package com.uninter.gerenciadorunidade.entrypoint.api.unit.dto;

import com.uninter.gerenciadorunidade.core.domain.unit.UnitDomain;
import com.uninter.gerenciadorunidade.core.domain.unit.UnitStatus;
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

    public static UnitResponse fromDomain(final UnitDomain domain) {
        return UnitResponse.builder()
            .id(domain.getId())
            .name(domain.getName())
            .address(domain.getAddress())
            .status(domain.getStatus())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }

}
