package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeDomain;
import com.uninter.gerenciadorunidade.core.gateway.EmployeeGateway;
import com.uninter.gerenciadorunidade.core.input.employee.UpdateEmployeeInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final EmployeeGateway employeeGateway;

    @Auditable(action = AuditAction.UPDATE)
    public EmployeeDomain execute(final UpdateEmployeeInput input) {
        var existing = findEmployeeByIdUseCase.execute(input.id());
        return employeeGateway.save(
            existing.update(input.name(), input.address(), input.birthDate(), input.type()));
    }

}
