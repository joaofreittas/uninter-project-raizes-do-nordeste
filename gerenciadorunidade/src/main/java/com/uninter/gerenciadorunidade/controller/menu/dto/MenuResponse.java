package com.uninter.gerenciadorunidade.controller.menu.dto;

import com.uninter.gerenciadorunidade.model.unit.Menu;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MenuResponse(
    Long id,
    Long unitId,
    List<MenuProductItem> products,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    @Builder
    public record MenuProductItem(
        Long productId,
        String name,
        BigDecimal price
    ) {

    }

    public static MenuResponse fromModel(final Menu menu) {
        var products = menu.getMenuProducts() == null ? List.<MenuProductItem>of()
            : menu.getMenuProducts().stream()
            .map(mp -> MenuProductItem.builder()
                .productId(mp.getProduct().getId())
                .name(mp.getProduct().getName())
                .price(mp.getProduct().getPrice())
                .build())
            .toList();

        return MenuResponse.builder()
            .id(menu.getId())
            .unitId(menu.getUnit().getId())
            .products(products)
            .createdAt(menu.getCreatedAt())
            .updatedAt(menu.getUpdatedAt())
            .build();
    }

}
