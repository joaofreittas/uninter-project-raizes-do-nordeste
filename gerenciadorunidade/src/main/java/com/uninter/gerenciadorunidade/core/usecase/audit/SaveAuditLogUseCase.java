package com.uninter.gerenciadorunidade.core.usecase.audit;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditLogDomain;
import com.uninter.gerenciadorunidade.core.gateway.AuditLogGateway;
import com.uninter.gerenciadorunidade.core.input.audit.SaveAuditLogInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAuditLogUseCase {

    private final AuditLogGateway auditLogGateway;

    public AuditLogDomain execute(final SaveAuditLogInput input) {
        return auditLogGateway.save(
            AuditLogDomain.create(input.userEmail(), input.action(),
                input.entity(), input.entityId(), input.occurredAt()));
    }

}
