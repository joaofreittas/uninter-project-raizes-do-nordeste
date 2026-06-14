package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.MenuDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.MenuProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveProductFromMenuUseCase {

    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final MenuProductGateway menuProductGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "Menu.removeProduct")
    public MenuDomain execute(final Long menuId, final Long productId) {
        findMenuByIdUseCase.execute(menuId);

        if (!menuProductGateway.existsById(menuId, productId)) {
            throw new DomainException("Product " + productId + " not found in menu " + menuId);
        }

        menuProductGateway.deleteById(menuId, productId);
        return findMenuByIdUseCase.execute(menuId);
    }

}
