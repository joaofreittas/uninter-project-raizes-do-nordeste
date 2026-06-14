package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditLogDomain;

import java.util.List;
import java.util.Optional;

public interface AuditLogGateway {

    AuditLogDomain save(AuditLogDomain log);

    Optional<AuditLogDomain> findById(Long id);

    List<AuditLogDomain> findAll();

}
