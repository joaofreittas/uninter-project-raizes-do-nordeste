package com.uninter.gerenciadorunidade.core.input.employee;

import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeType;

import java.time.LocalDate;

public record UpdateEmployeeInput(
    Long id,
    String name,
    String address,
    LocalDate birthDate,
    EmployeeType type
) {

}
