package com.uninter.gerenciadorunidade.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDomain {

    private Long id;
    private Long unitId;
    private List<MenuProductItemDomain> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuProductItemDomain {
        private Long productId;
        private String name;
        private BigDecimal price;
    }

}
