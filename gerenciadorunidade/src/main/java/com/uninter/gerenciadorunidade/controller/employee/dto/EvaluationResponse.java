package com.uninter.gerenciadorunidade.controller.employee.dto;

import com.uninter.gerenciadorunidade.model.unit.Evaluation;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EvaluationResponse(
    Long id,
    Integer rating,
    String comment,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static EvaluationResponse fromModel(final Evaluation evaluation) {
        return EvaluationResponse.builder()
            .id(evaluation.getId())
            .rating(evaluation.getRating())
            .comment(evaluation.getComment())
            .createdAt(evaluation.getCreatedAt())
            .updatedAt(evaluation.getUpdatedAt())
            .build();
    }

}
