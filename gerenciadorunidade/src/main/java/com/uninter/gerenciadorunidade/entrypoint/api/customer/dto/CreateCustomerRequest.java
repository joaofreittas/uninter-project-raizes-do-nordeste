package com.uninter.gerenciadorunidade.entrypoint.api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCustomerRequest(
    @NotBlank String name,
    @NotBlank String document,
    @NotBlank @Email String email,
    String phone,
    LocalDate birthDate,
    @NotNull Boolean lgpdAccepted,
    Boolean marketingAccepted
) {

}
