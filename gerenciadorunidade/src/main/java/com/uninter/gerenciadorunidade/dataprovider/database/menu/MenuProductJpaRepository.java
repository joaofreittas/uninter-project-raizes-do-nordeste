package com.uninter.gerenciadorunidade.dataprovider.database.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuProductJpaRepository extends JpaRepository<MenuProductEntity, MenuProductEntityId> {

    List<MenuProductEntity> findByIdMenuId(Long menuId);

}
