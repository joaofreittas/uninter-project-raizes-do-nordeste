package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.unit.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findByEmployeeId(Long employeeId);

}
