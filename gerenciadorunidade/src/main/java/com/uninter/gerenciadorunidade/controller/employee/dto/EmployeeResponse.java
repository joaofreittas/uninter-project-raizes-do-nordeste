package com.uninter.gerenciadorunidade.controller.employee.dto;

import com.uninter.gerenciadorunidade.model.unit.Employee;
import com.uninter.gerenciadorunidade.model.unit.EmployeeType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EmployeeResponse(
    Long id,
    Long unitId,
    String name,
    String address,
    String document,
    LocalDate birthDate,
    EmployeeType type,
    List<EvaluationResponse> evaluations,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static EmployeeResponse fromModel(final Employee employee) {
        var evaluations = employee.getEvaluations() == null ? List.<EvaluationResponse>of()
            : employee.getEvaluations().stream()
            .map(EvaluationResponse::fromModel)
            .toList();

        return EmployeeResponse.builder()
            .id(employee.getId())
            .unitId(employee.getUnit().getId())
            .name(employee.getName())
            .address(employee.getAddress())
            .document(employee.getDocument())
            .birthDate(employee.getBirthDate())
            .type(employee.getType())
            .evaluations(evaluations)
            .createdAt(employee.getCreatedAt())
            .updatedAt(employee.getUpdatedAt())
            .build();
    }

}
