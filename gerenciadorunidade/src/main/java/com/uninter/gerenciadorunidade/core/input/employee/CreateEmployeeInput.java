package com.uninter.gerenciadorunidade.core.input.employee;

import com.uninter.gerenciadorunidade.core.domain.employee.EmployeeType;

import java.time.LocalDate;

public record CreateEmployeeInput(
    Long unitId,
    String name,
    String address,
    String document,
    LocalDate birthDate,
    EmployeeType type
) {

}
