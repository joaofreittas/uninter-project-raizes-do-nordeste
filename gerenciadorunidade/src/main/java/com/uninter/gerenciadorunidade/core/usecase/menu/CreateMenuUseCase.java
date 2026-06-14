package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.MenuDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.MenuGateway;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateMenuUseCase {

    private final MenuGateway menuGateway;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public MenuDomain execute(final Long unitId) {
        unitGateway.findById(unitId)
            .orElseThrow(() -> new DomainException("Unit not found: " + unitId));

        if (!menuGateway.findByUnitId(unitId).isEmpty()) {
            throw new DomainException("Unit " + unitId + " already has a menu");
        }

        var menu = MenuDomain.builder()
            .unitId(unitId)
            .createdAt(LocalDateTime.now())
            .build();

        return menuGateway.save(menu);
    }

}
