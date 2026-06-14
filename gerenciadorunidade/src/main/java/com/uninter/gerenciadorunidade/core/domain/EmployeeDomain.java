package com.uninter.gerenciadorunidade.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDomain {

    private Long id;
    private Long unitId;
    private String name;
    private String address;
    private String document;
    private LocalDate birthDate;
    private EmployeeType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<EvaluationDomain> evaluations;

}
