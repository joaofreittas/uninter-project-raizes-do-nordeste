package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import com.uninter.gerenciadorunidade.core.input.customer.UpdateCustomerInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerGateway customerGateway;

    @Auditable(action = AuditAction.UPDATE)
    public CustomerDomain execute(final UpdateCustomerInput input) {
        var existing = findCustomerByIdUseCase.execute(input.id());
        return customerGateway.save(
            existing.update(input.name(), input.phone(), input.birthDate(), input.marketingAccepted()));
    }

}
