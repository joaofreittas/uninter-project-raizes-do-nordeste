package com.uninter.gerenciadorunidade.audit;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;

import java.time.LocalDateTime;

public record AuditEvent(
    String userEmail,
    AuditAction action,
    String entity,
    Long entityId,
    LocalDateTime occurredAt
) {

}
