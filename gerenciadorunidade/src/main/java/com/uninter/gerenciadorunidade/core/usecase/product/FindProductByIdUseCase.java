package com.uninter.gerenciadorunidade.core.usecase.product;

import com.uninter.gerenciadorunidade.core.domain.product.ProductDomain;
import com.uninter.gerenciadorunidade.core.exception.ResourceNotFoundException;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindProductByIdUseCase {

    private final ProductGateway productGateway;

    public ProductDomain execute(final Long id) {
        return productGateway.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

}
