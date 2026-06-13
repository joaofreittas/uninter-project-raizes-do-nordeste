package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.controller.unit.dto.CreateUnitRequest;
import com.uninter.gerenciadorunidade.controller.unit.dto.UpdateUnitRequest;
import com.uninter.gerenciadorunidade.model.unit.Unit;
import com.uninter.gerenciadorunidade.model.unit.UnitStatus;
import com.uninter.gerenciadorunidade.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    @Auditable(action = AuditAction.CREATE)
    public Unit create(final CreateUnitRequest request) {
        var now = LocalDateTime.now();
        var unit = Unit.builder()
            .name(request.name())
            .address(request.address())
            .status(UnitStatus.ACTIVATED)
            .createdAt(now)
            .build();
        return unitRepository.save(unit);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public Unit findById(final Long id) {
        return unitRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + id));
    }

    @Auditable(action = AuditAction.UPDATE)
    public Unit update(final Long id, final UpdateUnitRequest request) {
        var existing = findById(id);

        var updated = Unit.builder()
            .id(existing.getId())
            .name(request.name())
            .address(request.address())
            .status(existing.getStatus())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return unitRepository.save(updated);
    }

    @Auditable(action = AuditAction.DEACTIVATE)
    public Unit deactivate(final Long id) {
        var existing = findById(id);
        existing.deactivate();

        return unitRepository.save(existing);
    }

    @Auditable(action = AuditAction.DELETE)
    public void delete(final Long id) {
        var unit = findById(id);
        unitRepository.delete(unit);
    }

}
