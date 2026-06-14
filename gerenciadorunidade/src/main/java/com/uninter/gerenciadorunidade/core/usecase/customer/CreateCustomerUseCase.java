package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import com.uninter.gerenciadorunidade.core.input.customer.CreateCustomerInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    @Auditable(action = AuditAction.CREATE)
    public CustomerDomain execute(final CreateCustomerInput input) {
        if (customerGateway.findByDocument(input.document()).isPresent()) {
            throw new DomainException("Document already registered: " + input.document());
        }
        if (customerGateway.findByEmail(input.email()).isPresent()) {
            throw new DomainException("Email already registered: " + input.email());
        }

        return customerGateway.save(
            CustomerDomain.create(input.name(), input.document(), input.email(),
                input.phone(), input.birthDate(), input.lgpdAccepted(), input.marketingAccepted()));
    }

}
