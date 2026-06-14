package com.uninter.gerenciadorunidade.core.usecase.product;

import com.uninter.gerenciadorunidade.core.domain.ProductDomain;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllProductsUseCase {

    private final ProductGateway productGateway;

    public List<ProductDomain> execute() {
        return productGateway.findAll();
    }

}
