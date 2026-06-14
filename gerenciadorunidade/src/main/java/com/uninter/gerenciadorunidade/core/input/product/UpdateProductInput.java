package com.uninter.gerenciadorunidade.core.input.product;

import java.math.BigDecimal;

public record UpdateProductInput(Long id, String name, BigDecimal price) {

}
