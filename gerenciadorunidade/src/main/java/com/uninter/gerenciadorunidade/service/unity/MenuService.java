package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.audit.AuditAction;
import com.uninter.gerenciadorunidade.audit.Auditable;
import com.uninter.gerenciadorunidade.controller.menu.dto.AddProductToMenuRequest;
import com.uninter.gerenciadorunidade.controller.menu.dto.CreateMenuRequest;
import com.uninter.gerenciadorunidade.model.unit.Menu;
import com.uninter.gerenciadorunidade.model.unit.MenuProduct;
import com.uninter.gerenciadorunidade.model.unit.MenuProductId;
import com.uninter.gerenciadorunidade.repository.MenuProductRepository;
import com.uninter.gerenciadorunidade.repository.MenuRepository;
import com.uninter.gerenciadorunidade.repository.ProductRepository;
import com.uninter.gerenciadorunidade.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuProductRepository menuProductRepository;
    private final UnitRepository unitRepository;
    private final ProductRepository productRepository;

    @Auditable(action = AuditAction.CREATE)
    public Menu create(final CreateMenuRequest request) {
        var unit = unitRepository.findById(request.unitId())
            .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + request.unitId()));

        if (!menuRepository.findByUnitId(request.unitId()).isEmpty()) {
            throw new IllegalArgumentException("Unit " + request.unitId() + " already has a menu");
        }

        var now = LocalDateTime.now();
        var menu = Menu.builder()
            .unit(unit)
            .createdAt(now)
            .build();

        return menuRepository.save(menu);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public List<Menu> findByUnitId(final Long unitId) {
        return menuRepository.findByUnitId(unitId);
    }

    public Menu findById(final Long id) {
        return menuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Menu not found: " + id));
    }

    @Auditable(action = AuditAction.UPDATE, entity = "Menu.addProduct")
    public Menu addProduct(final Long id, final AddProductToMenuRequest request) {
        var menu = findById(id);
        var product = productRepository.findById(request.productId())
            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + request.productId()));

        var menuProductId = new MenuProductId(menu.getId(), product.getId());

        if (menuProductRepository.existsById(menuProductId)) {
            throw new IllegalArgumentException("Product " + product.getId() + " is already in menu " + id);
        }

        var menuProduct = MenuProduct.builder()
            .id(menuProductId)
            .menu(menu)
            .product(product)
            .createdAt(LocalDateTime.now())
            .build();

        menuProductRepository.save(menuProduct);
        menu.updateNow();
        menuRepository.save(menu);

        return menuRepository.findById(id).orElseThrow();
    }

    @Auditable(action = AuditAction.UPDATE, entity = "Menu.removeProduct")
    public Menu removeProduct(final Long id, final Long productId) {
        var menu = findById(id);
        var menuProductId = new MenuProductId(menu.getId(), productId);

        if (!menuProductRepository.existsById(menuProductId)) {
            throw new EntityNotFoundException("Product " + productId + " not found in menu " + id);
        }

        menuProductRepository.deleteById(menuProductId);

        return menuRepository.findById(id).orElseThrow();
    }

    @Auditable(action = AuditAction.DELETE)
    public void delete(final Long id) {
        var menu = findById(id);
        menuRepository.delete(menu);
    }

}
