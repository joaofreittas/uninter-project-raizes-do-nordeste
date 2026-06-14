package com.uninter.gerenciadorunidade.entrypoint.api.product;

import com.uninter.gerenciadorunidade.core.usecase.product.CreateProductUseCase;
import com.uninter.gerenciadorunidade.core.usecase.product.DeleteProductUseCase;
import com.uninter.gerenciadorunidade.core.usecase.product.FindAllProductsUseCase;
import com.uninter.gerenciadorunidade.core.usecase.product.FindProductByIdUseCase;
import com.uninter.gerenciadorunidade.core.usecase.product.UpdateProductUseCase;
import com.uninter.gerenciadorunidade.entrypoint.api.product.dto.CreateProductRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.product.dto.ProductResponse;
import com.uninter.gerenciadorunidade.entrypoint.api.product.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFacade {

    private final CreateProductUseCase createProductUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductResponse create(final CreateProductRequest request) {
        return ProductResponse.fromDomain(createProductUseCase.execute(request.name(), request.price()));
    }

    public List<ProductResponse> findAll() {
        return findAllProductsUseCase.execute().stream().map(ProductResponse::fromDomain).toList();
    }

    public ProductResponse findById(final Long id) {
        return ProductResponse.fromDomain(findProductByIdUseCase.execute(id));
    }

    public ProductResponse update(final Long id, final UpdateProductRequest request) {
        return ProductResponse.fromDomain(updateProductUseCase.execute(id, request.name(), request.price()));
    }

    public void delete(final Long id) {
        deleteProductUseCase.execute(id);
    }

}
