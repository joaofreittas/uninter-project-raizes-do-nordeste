package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMenuUseCase {

    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final MenuGateway menuGateway;

    @Auditable(action = AuditAction.DELETE)
    public void execute(final Long id) {
        var menu = findMenuByIdUseCase.execute(id);
        menuGateway.delete(menu);
    }

}
