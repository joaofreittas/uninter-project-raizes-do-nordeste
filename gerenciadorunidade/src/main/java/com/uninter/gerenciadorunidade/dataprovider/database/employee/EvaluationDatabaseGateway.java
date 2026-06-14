package com.uninter.gerenciadorunidade.dataprovider.database.employee;

import com.uninter.gerenciadorunidade.core.domain.employee.EvaluationDomain;
import com.uninter.gerenciadorunidade.core.gateway.EvaluationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EvaluationDatabaseGateway implements EvaluationGateway {

    private final EvaluationJpaRepository evaluationRepository;
    private final EmployeeJpaRepository employeeRepository;

    @Override
    public EvaluationDomain save(final EvaluationDomain evaluation) {
        var employeeEntity = employeeRepository.findById(evaluation.getEmployeeId()).orElse(null);
        return evaluationRepository.save(EvaluationEntity.from(evaluation, employeeEntity)).toDomain();
    }

    @Override
    public List<EvaluationDomain> findByEmployeeId(final Long employeeId) {
        return evaluationRepository.findByEmployeeId(employeeId).stream()
            .map(EvaluationEntity::toDomain).toList();
    }

}
