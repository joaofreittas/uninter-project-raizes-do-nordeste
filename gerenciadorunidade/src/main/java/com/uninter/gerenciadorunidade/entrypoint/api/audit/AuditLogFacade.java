package com.uninter.gerenciadorunidade.entrypoint.api.audit;

import com.uninter.gerenciadorunidade.core.usecase.audit.FindAllAuditLogsUseCase;
import com.uninter.gerenciadorunidade.core.usecase.audit.FindAuditLogByIdUseCase;
import com.uninter.gerenciadorunidade.entrypoint.api.audit.dto.AuditLogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuditLogFacade {

    private final FindAllAuditLogsUseCase findAllAuditLogsUseCase;
    private final FindAuditLogByIdUseCase findAuditLogByIdUseCase;

    public List<AuditLogResponse> findAll() {
        return findAllAuditLogsUseCase.execute().stream().map(AuditLogResponse::fromDomain).toList();
    }

    public AuditLogResponse findById(final Long id) {
        return AuditLogResponse.fromDomain(findAuditLogByIdUseCase.execute(id));
    }

}
