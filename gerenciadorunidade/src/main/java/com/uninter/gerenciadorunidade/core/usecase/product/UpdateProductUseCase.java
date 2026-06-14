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
public class UpdateProductUseCase {

    private final FindProductByIdUseCase findProductByIdUseCase;
    private final ProductGateway productGateway;

    @Auditable(action = AuditAction.UPDATE)
    public ProductDomain execute(final Long id, final String name, final BigDecimal price) {
        var existing = findProductByIdUseCase.execute(id);
        return productGateway.save(existing.update(name, price));
    }

}
