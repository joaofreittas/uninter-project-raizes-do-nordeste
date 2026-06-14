package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.core.domain.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.core.domain.menu.MenuDomain;
import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.MenuGateway;
import com.uninter.gerenciadorunidade.core.gateway.MenuProductGateway;
import com.uninter.gerenciadorunidade.core.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddProductToMenuUseCase {

    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final ProductGateway productGateway;
    private final MenuProductGateway menuProductGateway;
    private final MenuGateway menuGateway;

    @Auditable(action = AuditAction.UPDATE, entity = "Menu.addProduct")
    public MenuDomain execute(final Long menuId, final Long productId) {
        var menu = findMenuByIdUseCase.execute(menuId);
        productGateway.findById(productId)
            .orElseThrow(() -> new DomainException("Product not found: " + productId));

        if (menuProductGateway.existsById(menuId, productId)) {
            throw new DomainException("Product " + productId + " is already in menu " + menuId);
        }

        menuProductGateway.save(menuId, productId);

        var updated = MenuDomain.builder()
            .id(menu.getId())
            .unitId(menu.getUnitId())
            .createdAt(menu.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();
        menuGateway.save(updated);

        return findMenuByIdUseCase.execute(menuId);
    }

}
