package com.uninter.gerenciadorunidade.core.usecase.employee;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.EmployeeDomain;
import com.uninter.gerenciadorunidade.core.domain.EmployeeType;
import com.uninter.gerenciadorunidade.core.gateway.EmployeeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final EmployeeGateway employeeGateway;

    @Auditable(action = AuditAction.UPDATE)
    public EmployeeDomain execute(final Long id, final String name, final String address,
                                  final LocalDate birthDate, final EmployeeType type) {
        var existing = findEmployeeByIdUseCase.execute(id);
        var updated = EmployeeDomain.builder()
            .id(existing.getId())
            .unitId(existing.getUnitId())
            .name(name)
            .address(address)
            .document(existing.getDocument())
            .birthDate(birthDate)
            .type(type)
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return employeeGateway.save(updated);
    }

}
