package com.uninter.gerenciadorunidade.entrypoint.api.promotion;

import com.uninter.gerenciadorunidade.core.input.promotion.CreatePromotionInput;
import com.uninter.gerenciadorunidade.core.input.promotion.UpdatePromotionInput;
import com.uninter.gerenciadorunidade.core.usecase.promotion.CreatePromotionUseCase;
import com.uninter.gerenciadorunidade.core.usecase.promotion.DeletePromotionUseCase;
import com.uninter.gerenciadorunidade.core.usecase.promotion.FindAllPromotionsUseCase;
import com.uninter.gerenciadorunidade.core.usecase.promotion.FindPromotionByIdUseCase;
import com.uninter.gerenciadorunidade.core.usecase.promotion.UpdatePromotionUseCase;
import com.uninter.gerenciadorunidade.entrypoint.api.promotion.dto.CreatePromotionRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.promotion.dto.PromotionResponse;
import com.uninter.gerenciadorunidade.entrypoint.api.promotion.dto.UpdatePromotionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromotionFacade {

    private final CreatePromotionUseCase createPromotionUseCase;
    private final FindAllPromotionsUseCase findAllPromotionsUseCase;
    private final FindPromotionByIdUseCase findPromotionByIdUseCase;
    private final UpdatePromotionUseCase updatePromotionUseCase;
    private final DeletePromotionUseCase deletePromotionUseCase;

    public PromotionResponse create(final CreatePromotionRequest request) {
        var domain = createPromotionUseCase.execute(new CreatePromotionInput(
            request.unitId(), request.title(), request.startDate(), request.endDate(), request.reward()));
        return PromotionResponse.fromDomain(domain);
    }

    public List<PromotionResponse> findAll() {
        return findAllPromotionsUseCase.execute().stream().map(PromotionResponse::fromDomain).toList();
    }

    public PromotionResponse findById(final Long id) {
        return PromotionResponse.fromDomain(findPromotionByIdUseCase.execute(id));
    }

    public PromotionResponse update(final Long id, final UpdatePromotionRequest request) {
        var domain = updatePromotionUseCase.execute(new UpdatePromotionInput(
            id, request.title(), request.startDate(), request.endDate(), request.reward()));
        return PromotionResponse.fromDomain(domain);
    }

    public void delete(final Long id) {
        deletePromotionUseCase.execute(id);
    }

}
