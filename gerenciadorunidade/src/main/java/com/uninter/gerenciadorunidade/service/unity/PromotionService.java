package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.controller.promotion.dto.CreatePromotionRequest;
import com.uninter.gerenciadorunidade.controller.promotion.dto.UpdatePromotionRequest;
import com.uninter.gerenciadorunidade.model.unit.Promotion;
import com.uninter.gerenciadorunidade.repository.PromotionRepository;
import com.uninter.gerenciadorunidade.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final UnitRepository unitRepository;

    public Promotion create(final CreatePromotionRequest request) {
        if (!request.startDate().isBefore(request.endDate())) {
            throw new IllegalArgumentException("startDate deve ser anterior a endDate");
        }

        var unit = unitRepository.findById(request.unitId())
            .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + request.unitId()));

        var now = LocalDateTime.now();
        var promotion = Promotion.builder()
            .unit(unit)
            .title(request.title())
            .startDate(request.startDate())
            .endDate(request.endDate())
            .reward(request.reward())
            .createdAt(now)
            .build();

        return promotionRepository.save(promotion);
    }

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    public Promotion findById(final Long id) {
        return promotionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Promotion not found: " + id));
    }

    public Promotion update(final Long id, final UpdatePromotionRequest request) {
        if (!request.startDate().isBefore(request.endDate())) {
            throw new IllegalArgumentException("startDate deve ser anterior a endDate");
        }

        var existing = findById(id);
        var updated = Promotion.builder()
            .id(existing.getId())
            .unit(existing.getUnit())
            .title(request.title())
            .startDate(request.startDate())
            .endDate(request.endDate())
            .reward(request.reward())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return promotionRepository.save(updated);
    }

    public void delete(final Long id) {
        var promotion = findById(id);
        promotionRepository.delete(promotion);
    }

}
