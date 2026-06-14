package com.uninter.gerenciadorunidade.core.usecase.menu;

import com.uninter.gerenciadorunidade.core.domain.MenuDomain;
import com.uninter.gerenciadorunidade.core.exception.ResourceNotFoundException;
import com.uninter.gerenciadorunidade.core.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMenuByIdUseCase {

    private final MenuGateway menuGateway;

    public MenuDomain execute(final Long id) {
        return menuGateway.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Menu not found: " + id));
    }

}
