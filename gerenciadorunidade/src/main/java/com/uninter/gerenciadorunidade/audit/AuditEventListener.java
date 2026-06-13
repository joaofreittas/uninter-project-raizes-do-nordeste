package com.uninter.gerenciadorunidade.audit;

import com.uninter.gerenciadorunidade.model.AuditLog;
import com.uninter.gerenciadorunidade.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventListener {

    private final AuditLogRepository auditLogRepository;

    @Async
    @EventListener
    public void onAuditEvent(AuditEvent event) {
        var log = AuditLog.builder()
            .userEmail(event.userEmail())
            .action(event.action())
            .entity(event.entity())
            .occurredAt(event.occurredAt())
            .build();

        auditLogRepository.save(log);
    }

}
