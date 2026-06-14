package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeDomain;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeType;
import com.uninter.gerenciadorunidade.core.gateway.EmployeeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final EmployeeGateway employeeGateway;

    @Auditable(action = AuditAction.UPDATE)
    public EmployeeDomain execute(final Long id, final String name, final String address,
                                  final LocalDate birthDate, final EmployeeType type) {
        var existing = findEmployeeByIdUseCase.execute(id);
        return employeeGateway.save(existing.update(name, address, birthDate, type));
    }

}
