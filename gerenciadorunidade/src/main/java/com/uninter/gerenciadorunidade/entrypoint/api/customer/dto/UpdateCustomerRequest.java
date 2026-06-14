package com.uninter.gerenciadorunidade.entrypoint.api.customer.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateCustomerRequest(
    @NotBlank String name,
    String phone,
    LocalDate birthDate,
    Boolean marketingAccepted
) {

}
