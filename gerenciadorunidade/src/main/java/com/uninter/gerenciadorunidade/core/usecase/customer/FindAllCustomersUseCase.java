package com.uninter.gerenciadorunidade.core.usecase.customer;

import com.uninter.gerenciadorunidade.core.domain.customer.CustomerDomain;
import com.uninter.gerenciadorunidade.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCustomersUseCase {

    private final CustomerGateway customerGateway;

    public List<CustomerDomain> execute() {
        return customerGateway.findAll();
    }

}
