package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.employee.EvaluationDomain;
import com.uninter.gerenciadorunidade.core.gateway.EvaluationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddEvaluationUseCase {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final EvaluationGateway evaluationGateway;

    @Auditable(action = AuditAction.CREATE, entity = "Evaluation")
    public EvaluationDomain execute(final Long employeeId, final Integer rating, final String comment) {
        findEmployeeByIdUseCase.execute(employeeId);
        return evaluationGateway.save(EvaluationDomain.create(employeeId, rating, comment));
    }

}
