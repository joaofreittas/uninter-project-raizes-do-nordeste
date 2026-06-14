package com.uninter.gerenciadorunidade.core.domain.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemDomain {

    private Long id;
    private Long unitId;
    private Long productId;
    private Integer quantity;
    private Integer minimumQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
