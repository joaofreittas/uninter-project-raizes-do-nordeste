package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeDomain;
import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeType;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.EmployeeGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {

    private final EmployeeGateway employeeGateway;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public EmployeeDomain execute(final Long unitId, final String name, final String address,
                                  final String document, final LocalDate birthDate, final EmployeeType type) {
        unitGateway.findById(unitId)
            .orElseThrow(() -> new DomainException("Unit not found: " + unitId));

        if (employeeGateway.findByDocument(document).isPresent()) {
            throw new DomainException("Document already registered: " + document);
        }

        return employeeGateway.save(EmployeeDomain.create(unitId, name, address, document, birthDate, type));
    }

}
