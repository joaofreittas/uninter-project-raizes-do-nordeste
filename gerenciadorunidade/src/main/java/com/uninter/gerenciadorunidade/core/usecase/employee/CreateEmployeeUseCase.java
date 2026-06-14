package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.EmployeeGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import com.uninter.gerenciadorunidade.core.input.employee.CreateEmployeeInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {

    private final EmployeeGateway employeeGateway;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public EmployeeDomain execute(final CreateEmployeeInput input) {
        unitGateway.findById(input.unitId())
            .orElseThrow(() -> new DomainException("Unit not found: " + input.unitId()));

        if (employeeGateway.findByDocument(input.document()).isPresent()) {
            throw new DomainException("Document already registered: " + input.document());
        }

        return employeeGateway.save(
            EmployeeDomain.create(input.unitId(), input.name(), input.address(),
                input.document(), input.birthDate(), input.type()));
    }

}
