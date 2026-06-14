package com.uninter.gerenciadorunidade.core.usecase.unit;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.UnitDomain;
import com.uninter.gerenciadorunidade.core.domain.UnitStatus;
import com.uninter.gerenciadorunidade.core.gateway.UnitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateUnitUseCase {

    private final UnitGateway unitGateway;

    @Auditable(action = AuditAction.CREATE)
    public UnitDomain execute(final String name, final String address) {
        var now = LocalDateTime.now();
        var unit = UnitDomain.builder()
            .name(name)
            .address(address)
            .status(UnitStatus.ACTIVATED)
            .createdAt(now)
            .build();
        return unitGateway.save(unit);
    }

}
