package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.controller.product.dto.CreateProductRequest;
import com.uninter.gerenciadorunidade.controller.product.dto.UpdateProductRequest;
import com.uninter.gerenciadorunidade.model.unit.Product;
import com.uninter.gerenciadorunidade.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Auditable(action = AuditAction.CREATE)
    public Product create(final CreateProductRequest request) {
        var now = LocalDateTime.now();
        var product = Product.builder()
            .name(request.name())
            .price(request.price())
            .createdAt(now)
            .build();

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(final Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    @Auditable(action = AuditAction.UPDATE)
    public Product update(final Long id, final UpdateProductRequest request) {
        var existing = findById(id);
        var updated = Product.builder()
            .id(existing.getId())
            .name(request.name())
            .price(request.price())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return productRepository.save(updated);
    }

    @Auditable(action = AuditAction.DELETE)
    public void delete(final Long id) {
        var product = findById(id);
        productRepository.delete(product);
    }

}
