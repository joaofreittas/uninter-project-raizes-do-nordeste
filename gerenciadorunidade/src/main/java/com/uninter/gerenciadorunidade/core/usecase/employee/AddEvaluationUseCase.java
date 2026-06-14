package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.EvaluationDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.EvaluationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddEvaluationUseCase {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final EvaluationGateway evaluationGateway;

    @Auditable(action = AuditAction.CREATE, entity = "Evaluation")
    public EvaluationDomain execute(final Long employeeId, final Integer rating, final String comment) {
        if (rating < 1 || rating > 5) {
            throw new DomainException("Rating must be between 1 and 5");
        }
        findEmployeeByIdUseCase.execute(employeeId);

        var evaluation = EvaluationDomain.builder()
            .employeeId(employeeId)
            .rating(rating)
            .comment(comment)
            .createdAt(LocalDateTime.now())
            .build();

        return evaluationGateway.save(evaluation);
    }

}
