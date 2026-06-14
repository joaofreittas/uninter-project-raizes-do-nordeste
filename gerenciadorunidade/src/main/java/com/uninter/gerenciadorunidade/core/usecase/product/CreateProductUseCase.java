package com.uninter.gerenciadorunidade.core.usecase.product;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.product.ProductDomain;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway productGateway;

    @Auditable(action = AuditAction.CREATE)
    public ProductDomain execute(final String name, final BigDecimal price) {
        return productGateway.save(ProductDomain.create(name, price));
    }

}
