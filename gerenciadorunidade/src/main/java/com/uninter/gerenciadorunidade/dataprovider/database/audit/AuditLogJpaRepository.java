package com.uninter.gerenciadorunidade.dataprovider.database.audit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogJpaRepository extends JpaRepository<AuditLogEntity, Long> {

}
