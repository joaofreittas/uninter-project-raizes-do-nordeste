package com.uninter.gerenciadorunidade.core.usecase.audit;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditLogDomain;
import com.uninter.gerenciadorunidade.core.gateway.AuditLogGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaveAuditLogUseCase {

    private final AuditLogGateway auditLogGateway;

    public AuditLogDomain execute(final String userEmail, final AuditAction action,
                                  final String entity, final Long entityId,
                                  final LocalDateTime occurredAt) {
        var log = AuditLogDomain.builder()
            .userEmail(userEmail)
            .action(action)
            .entity(entity)
            .entityId(entityId)
            .occurredAt(occurredAt)
            .build();
        return auditLogGateway.save(log);
    }

}
