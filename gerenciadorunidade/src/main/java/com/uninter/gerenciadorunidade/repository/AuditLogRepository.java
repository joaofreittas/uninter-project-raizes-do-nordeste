package com.uninter.gerenciadorunidade.repository;

import com.uninter.gerenciadorunidade.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
