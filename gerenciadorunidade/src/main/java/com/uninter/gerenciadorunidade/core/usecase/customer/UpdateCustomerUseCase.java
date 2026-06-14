package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerGateway customerGateway;

    @Auditable(action = AuditAction.UPDATE)
    public CustomerDomain execute(final Long id, final String name, final String phone,
        final LocalDate birthDate, final Boolean marketingAccepted) {
        var existing = findCustomerByIdUseCase.execute(id);
        return customerGateway.save(existing.update(name, phone, birthDate, marketingAccepted));
    }

}
