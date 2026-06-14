package com.uninter.gerenciadorunidade.entrypoint.api.product.dto;

import com.uninter.gerenciadorunidade.core.domain.product.ProductDomain;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(
    Long id,
    String name,
    BigDecimal price,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static ProductResponse fromDomain(final ProductDomain domain) {
        return ProductResponse.builder()
            .id(domain.getId())
            .name(domain.getName())
            .price(domain.getPrice())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }

}
