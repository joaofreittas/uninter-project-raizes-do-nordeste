package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.payment.PaymentDomain;

import java.util.List;
import java.util.Optional;

public interface PaymentGateway {

    PaymentDomain save(PaymentDomain payment);

    Optional<PaymentDomain> findById(Long id);

    List<PaymentDomain> findByOrderId(Long orderId);

    List<PaymentDomain> findAll();

    void delete(PaymentDomain payment);

}
