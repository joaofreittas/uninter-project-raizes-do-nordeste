package com.uninter.gerenciadorunidade.core.input.customer;

import java.time.LocalDate;

public record CreateCustomerInput(
    String name,
    String document,
    String email,
    String phone,
    LocalDate birthDate,
    Boolean lgpdAccepted,
    Boolean marketingAccepted
) {

}
