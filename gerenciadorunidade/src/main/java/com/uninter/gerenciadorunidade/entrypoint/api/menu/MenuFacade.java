package com.uninter.gerenciadorunidade.entrypoint.api.menu;

import com.uninter.gerenciadorunidade.core.usecase.menu.AddProductToMenuUseCase;
import com.uninter.gerenciadorunidade.core.usecase.menu.CreateMenuUseCase;
import com.uninter.gerenciadorunidade.core.usecase.menu.DeleteMenuUseCase;
import com.uninter.gerenciadorunidade.core.usecase.menu.FindAllMenusUseCase;
import com.uninter.gerenciadorunidade.core.usecase.menu.FindMenuByIdUseCase;
import com.uninter.gerenciadorunidade.core.usecase.menu.RemoveProductFromMenuUseCase;
import com.uninter.gerenciadorunidade.entrypoint.api.menu.dto.AddProductToMenuRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.menu.dto.CreateMenuRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.menu.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuFacade {

    private final CreateMenuUseCase createMenuUseCase;
    private final FindAllMenusUseCase findAllMenusUseCase;
    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final AddProductToMenuUseCase addProductToMenuUseCase;
    private final RemoveProductFromMenuUseCase removeProductFromMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

    public MenuResponse create(final CreateMenuRequest request) {
        return MenuResponse.fromDomain(createMenuUseCase.execute(request.unitId()));
    }

    public List<MenuResponse> findAll(final Optional<Long> unitId) {
        return findAllMenusUseCase.execute(unitId).stream().map(MenuResponse::fromDomain).toList();
    }

    public MenuResponse findById(final Long id) {
        return MenuResponse.fromDomain(findMenuByIdUseCase.execute(id));
    }

    public MenuResponse addProduct(final Long menuId, final AddProductToMenuRequest request) {
        return MenuResponse.fromDomain(addProductToMenuUseCase.execute(menuId, request.productId()));
    }

    public MenuResponse removeProduct(final Long menuId, final Long productId) {
        return MenuResponse.fromDomain(removeProductFromMenuUseCase.execute(menuId, productId));
    }

    public void delete(final Long id) {
        deleteMenuUseCase.execute(id);
    }

}
