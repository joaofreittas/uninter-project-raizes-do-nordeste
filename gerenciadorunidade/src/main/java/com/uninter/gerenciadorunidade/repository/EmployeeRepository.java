package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByDocument(String document);

}
