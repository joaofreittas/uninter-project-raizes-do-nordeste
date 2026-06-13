package com.uninter.gerenciadorunidade.controller.employee.dto;

import com.uninter.gerenciadorunidade.model.unit.EmployeeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateEmployeeRequest(
    @NotNull Long unitId,
    @NotBlank String name,
    String address,
    @NotBlank String document,
    LocalDate birthDate,
    @NotNull EmployeeType type
) {

}
