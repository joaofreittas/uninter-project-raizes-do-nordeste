package com.uninter.gerenciadorunidade.core.usecase.product;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.ProductDomain;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway productGateway;

    @Auditable(action = AuditAction.CREATE)
    public ProductDomain execute(final String name, final BigDecimal price) {
        var now = LocalDateTime.now();
        var product = ProductDomain.builder()
            .name(name)
            .price(price)
            .createdAt(now)
            .build();
        return productGateway.save(product);
    }

}
