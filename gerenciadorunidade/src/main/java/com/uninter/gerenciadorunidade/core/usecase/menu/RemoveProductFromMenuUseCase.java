package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.core.domain.menu.MenuDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.MenuProductGateway;
import com.uninter.gerenciadorunidade.core.input.menu.RemoveProductFromMenuInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveProductFromMenuUseCase {

    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final MenuProductGateway menuProductGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "Menu.removeProduct")
    public MenuDomain execute(final RemoveProductFromMenuInput input) {
        findMenuByIdUseCase.execute(input.menuId());

        if (!menuProductGateway.existsById(input.menuId(), input.productId())) {
            throw new DomainException(
                "Product " + input.productId() + " not found in menu " + input.menuId());
        }

        menuProductGateway.deleteById(input.menuId(), input.productId());
        return findMenuByIdUseCase.execute(input.menuId());
    }

}
