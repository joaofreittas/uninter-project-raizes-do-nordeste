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
import java.time.LocalDateTime;

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

        var now = LocalDateTime.now();
        var employee = EmployeeDomain.builder()
            .unitId(unitId)
            .name(name)
            .address(address)
            .document(document)
            .birthDate(birthDate)
            .type(type)
            .createdAt(now)
            .build();

        return employeeGateway.save(employee);
    }

}
