package com.uninter.gerenciadorunidade.core.usecase.product;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final FindProductByIdUseCase findProductByIdUseCase;
    private final ProductGateway productGateway;

    @Auditable(action = AuditAction.DELETE)
    public void execute(final Long id) {
        var product = findProductByIdUseCase.execute(id);
        productGateway.delete(product);
    }

}
