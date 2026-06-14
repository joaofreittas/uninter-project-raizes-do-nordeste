package com.uninter.gerenciadorunidade.dataprovider.database.inventory;

import com.uninter.gerenciadorunidade.core.domain.InventoryItemDomain;
import com.uninter.gerenciadorunidade.core.gateway.InventoryItemGateway;
import com.uninter.gerenciadorunidade.dataprovider.database.product.ProductJpaRepository;
import com.uninter.gerenciadorunidade.dataprovider.database.unit.UnitJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InventoryItemDatabaseGateway implements InventoryItemGateway {

    private final InventoryItemJpaRepository repository;
    private final UnitJpaRepository unitRepository;
    private final ProductJpaRepository productRepository;

    @Override
    public InventoryItemDomain save(final InventoryItemDomain item) {
        var unitEntity = unitRepository.findById(item.getUnitId()).orElse(null);
        var productEntity = productRepository.findById(item.getProductId()).orElse(null);
        return repository.save(InventoryItemEntity.from(item, unitEntity, productEntity)).toDomain();
    }

    @Override
    public Optional<InventoryItemDomain> findById(final Long id) {
        return repository.findById(id).map(InventoryItemEntity::toDomain);
    }

    @Override
    public List<InventoryItemDomain> findAll() {
        return repository.findAll().stream().map(InventoryItemEntity::toDomain).toList();
    }

    @Override
    public List<InventoryItemDomain> findByUnitId(final Long unitId) {
        return repository.findByUnitId(unitId).stream().map(InventoryItemEntity::toDomain).toList();
    }

    @Override
    public List<InventoryItemDomain> findByProductId(final Long productId) {
        return repository.findByProductId(productId).stream().map(InventoryItemEntity::toDomain).toList();
    }

}
