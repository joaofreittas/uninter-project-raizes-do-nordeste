package com.uninter.gerenciadorunidade.core.usecase.audit;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditLogDomain;
import com.uninter.gerenciadorunidade.core.gateway.AuditLogGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllAuditLogsUseCase {

    private final AuditLogGateway auditLogGateway;

    public List<AuditLogDomain> execute() {
        return auditLogGateway.findAll();
    }

}
