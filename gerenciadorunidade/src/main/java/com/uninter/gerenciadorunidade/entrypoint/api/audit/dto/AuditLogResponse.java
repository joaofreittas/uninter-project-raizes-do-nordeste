package com.uninter.gerenciadorunidade.entrypoint.api.audit.dto;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.AuditLogDomain;
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

    public static AuditLogResponse fromDomain(final AuditLogDomain domain) {
        return AuditLogResponse.builder()
            .id(domain.getId())
            .userEmail(domain.getUserEmail())
            .action(domain.getAction())
            .entity(domain.getEntity())
            .entityId(domain.getEntityId())
            .occurredAt(domain.getOccurredAt())
            .build();
    }

}
