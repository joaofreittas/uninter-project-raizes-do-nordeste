package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.menu.MenuDomain;

import java.util.List;
import java.util.Optional;

public interface MenuGateway {

    MenuDomain save(MenuDomain menu);

    Optional<MenuDomain> findById(Long id);

    List<MenuDomain> findAll();

    List<MenuDomain> findByUnitId(Long unitId);

    void delete(MenuDomain menu);

}
