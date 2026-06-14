package com.uninter.gerenciadorunidade.dataprovider.database.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {

    List<MenuEntity> findByUnitId(Long unitId);

}
