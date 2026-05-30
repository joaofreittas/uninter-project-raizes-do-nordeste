package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.MenuProduct;
import com.uninter.gerenciadorunidade.model.unit.MenuProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuProductRepository extends JpaRepository<MenuProduct, MenuProductId> {

    List<MenuProduct> findByIdMenuId(Long menuId);

}
