package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerGateway customerGateway;

    @Auditable(action = AuditAction.UPDATE)
    public CustomerDomain execute(final Long id, final String name, final String phone,
        final LocalDate birthDate, final Boolean marketingAccepted) {
        var existing = findCustomerByIdUseCase.execute(id);
        var updated = CustomerDomain.builder()
            .id(existing.getId())
            .name(name)
            .document(existing.getDocument())
            .email(existing.getEmail())
            .phone(phone)
            .birthDate(birthDate)
            .lgpdAccepted(existing.getLgpdAccepted())
            .marketingAccepted(marketingAccepted)
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return customerGateway.save(updated);
    }

}
