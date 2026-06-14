package com.uninter.gerenciadorunidade.core.usecase.unit;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.unit.UnitDomain;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUnitUseCase {

    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public UnitDomain execute(final String name, final String address) {
        return unitGateway.save(UnitDomain.create(name, address));
    }

}
