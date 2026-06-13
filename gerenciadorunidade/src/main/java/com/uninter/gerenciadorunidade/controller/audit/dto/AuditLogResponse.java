package com.uninter.gerenciadorunidade.controller.audit.dto;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.model.AuditLog;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuditLogResponse(
    Long id,
    String userEmail,
    AuditAction action,
    String entity,
    LocalDateTime occurredAt
) {

    public static AuditLogResponse fromModel(final AuditLog auditLog) {
        return AuditLogResponse.builder()
            .id(auditLog.getId())
            .userEmail(auditLog.getUserEmail())
            .action(auditLog.getAction())
            .entity(auditLog.getEntity())
            .occurredAt(auditLog.getOccurredAt())
            .build();
    }

}
