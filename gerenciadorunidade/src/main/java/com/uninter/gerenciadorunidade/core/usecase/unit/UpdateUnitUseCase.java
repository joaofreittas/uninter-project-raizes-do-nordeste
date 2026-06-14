package com.uninter.gerenciadorunidade.core.usecase.unit;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.unit.UnitDomain;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUnitUseCase {

    private final FindUnitByIdUseCase findUnitByIdUseCase;
    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.UPDATE)
    public UnitDomain execute(final Long id, final String name, final String address) {
        var existing = findUnitByIdUseCase.execute(id);
        return unitGateway.save(existing.update(name, address));
    }

}
