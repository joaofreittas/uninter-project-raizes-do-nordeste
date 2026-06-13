package com.uninter.gerenciadorunidade.controller.product.dto;

import com.uninter.gerenciadorunidade.model.unit.Product;
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

    public static ProductResponse fromModel(final Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }

}
