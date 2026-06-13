package com.uninter.gerenciadorunidade.controller.audit.dto;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.model.audit.AuditLog;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuditLogResponse(
    Long id,
    String userEmail,
    AuditAction action,
    String entity,
    Long entityId,
    LocalDateTime occurredAt
) {

    public static AuditLogResponse fromModel(final AuditLog auditLog) {
        return AuditLogResponse.builder()
            .id(auditLog.getId())
            .userEmail(auditLog.getUserEmail())
            .action(auditLog.getAction())
            .entity(auditLog.getEntity())
            .entityId(auditLog.getEntityId())
            .occurredAt(auditLog.getOccurredAt())
            .build();
    }

}
