package com.uninter.gerenciadorunidade.dataprovider.database.audit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {

}
