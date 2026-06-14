package com.uninter.gerenciadorunidade.audit;

import com.uninter.gerenciadorunidade.core.input.audit.SaveAuditLogInput;
import com.uninter.gerenciadorunidade.core.usecase.audit.SaveAuditLogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventListener {

    private final SaveAuditLogUseCase saveAuditLogUseCase;

    @Async
    @EventListener
    public void onAuditEvent(AuditEvent event) {
        saveAuditLogUseCase.execute(new SaveAuditLogInput(
            event.userEmail(),
            event.action(),
            event.entity(),
            event.entityId(),
            event.occurredAt()
        ));
    }

}
