package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.product.ProductDomain;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    ProductDomain save(ProductDomain product);

    Optional<ProductDomain> findById(Long id);

    List<ProductDomain> findAll();

    void delete(ProductDomain product);

}
