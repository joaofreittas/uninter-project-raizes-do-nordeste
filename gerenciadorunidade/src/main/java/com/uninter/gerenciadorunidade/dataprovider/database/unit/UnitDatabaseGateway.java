package com.uninter.gerenciadorunidade.dataprovider.database.unit;

import com.uninter.gerenciadorunidade.core.domain.unit.UnitDomain;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UnitDatabaseGateway implements UnitGateway {

    private final UnitJpaRepository repository;

    @Override
    public UnitDomain save(final UnitDomain unit) {
        return repository.save(UnitEntity.from(unit)).toDomain();
    }

    @Override
    public Optional<UnitDomain> findById(final Long id) {
        return repository.findById(id).map(UnitEntity::toDomain);
    }

    @Override
    public List<UnitDomain> findAll() {
        return repository.findAll().stream().map(UnitEntity::toDomain).toList();
    }

    @Override
    public void delete(final UnitDomain unit) {
        repository.deleteById(unit.getId());
    }

}
