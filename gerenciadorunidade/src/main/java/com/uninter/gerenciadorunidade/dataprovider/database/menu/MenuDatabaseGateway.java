package com.uninter.gerenciadorunidade.dataprovider.database.menu;

import com.uninter.gerenciadorunidade.core.domain.menu.MenuDomain;
import com.uninter.gerenciadorunidade.core.gateway.MenuGateway;
import com.uninter.gerenciadorunidade.dataprovider.database.unit.UnitJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuDatabaseGateway implements MenuGateway {

    private final MenuJpaRepository menuRepository;
    private final UnitJpaRepository unitRepository;

    @Override
    public MenuDomain save(final MenuDomain menu) {
        var unitEntity = unitRepository.findById(menu.getUnitId()).orElse(null);
        return menuRepository.save(MenuEntity.from(menu, unitEntity)).toDomain();
    }

    @Override
    public Optional<MenuDomain> findById(final Long id) {
        return menuRepository.findById(id).map(MenuEntity::toDomain);
    }

    @Override
    public List<MenuDomain> findAll() {
        return menuRepository.findAll().stream().map(MenuEntity::toDomain).toList();
    }

    @Override
    public List<MenuDomain> findByUnitId(final Long unitId) {
        return menuRepository.findByUnitId(unitId).stream().map(MenuEntity::toDomain).toList();
    }

    @Override
    public void delete(final MenuDomain menu) {
        menuRepository.deleteById(menu.getId());
    }

}
