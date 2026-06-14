package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    @Auditable(action = AuditAction.CREATE)
    public CustomerDomain execute(final String name, final String document, final String email,
        final String phone, final LocalDate birthDate,
        final Boolean lgpdAccepted, final Boolean marketingAccepted) {
        if (customerGateway.findByDocument(document).isPresent()) {
            throw new DomainException("Document already registered: " + document);
        }
        if (customerGateway.findByEmail(email).isPresent()) {
            throw new DomainException("Email already registered: " + email);
        }

        var now = LocalDateTime.now();
        var customer = CustomerDomain.builder()
            .name(name)
            .document(document)
            .email(email)
            .phone(phone)
            .birthDate(birthDate)
            .lgpdAccepted(lgpdAccepted)
            .marketingAccepted(marketingAccepted)
            .createdAt(now)
            .updatedAt(now)
            .build();

        return customerGateway.save(customer);
    }

}
