package com.uninter.gerenciadorunidade.service.audit;

import com.uninter.gerenciadorunidade.model.AuditLog;
import com.uninter.gerenciadorunidade.repository.AuditLogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    public AuditLog findById(final Long id) {
        return auditLogRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("AuditLog not found: " + id));
    }

}
