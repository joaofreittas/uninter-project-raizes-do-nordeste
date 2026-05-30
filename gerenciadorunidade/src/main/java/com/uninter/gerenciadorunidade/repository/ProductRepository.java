package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
