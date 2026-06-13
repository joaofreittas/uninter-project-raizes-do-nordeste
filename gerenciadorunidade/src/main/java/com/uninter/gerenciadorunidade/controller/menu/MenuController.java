package com.uninter.gerenciadorunidade.controller.menu;

import com.uninter.gerenciadorunidade.controller.menu.dto.AddProductToMenuRequest;
import com.uninter.gerenciadorunidade.controller.menu.dto.CreateMenuRequest;
import com.uninter.gerenciadorunidade.controller.menu.dto.MenuResponse;
import com.uninter.gerenciadorunidade.service.unity.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
@Tag(name = "Cardápios", description = "Gerenciamento de cardápios por unidade")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "Criar cardápio para uma unidade")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cardápio criado com sucesso",
            content = @Content(schema = @Schema(implementation = MenuResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Unidade não encontrada", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MenuResponse> create(@Valid @RequestBody CreateMenuRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MenuResponse.fromModel(menuService.create(request)));
    }

    @Operation(summary = "Listar cardápios",
        description = "Retorna todos os cardápios. Filtre por unitId via query param.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<MenuResponse>> findAll(@RequestParam Optional<Long> unitId) {
        var menus = unitId.map(menuService::findByUnitId)
            .orElseGet(menuService::findAll);

        return ResponseEntity.ok(menus.stream().map(MenuResponse::fromModel).toList());
    }

    @Operation(summary = "Buscar cardápio por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cardápio encontrado",
            content = @Content(schema = @Schema(implementation = MenuResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cardápio não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(MenuResponse.fromModel(menuService.findById(id)));
    }

    @Operation(summary = "Adicionar produto ao cardápio")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso",
            content = @Content(schema = @Schema(implementation = MenuResponse.class))),
        @ApiResponse(responseCode = "400", description = "Produto já presente no cardápio", content = @Content),
        @ApiResponse(responseCode = "404", description = "Cardápio ou produto não encontrado", content = @Content)
    })
    @PostMapping("/{id}/products")
    public ResponseEntity<MenuResponse> addProduct(
        @PathVariable Long id,
        @Valid @RequestBody AddProductToMenuRequest request
                                                  ) {
        return ResponseEntity.ok(MenuResponse.fromModel(menuService.addProduct(id, request)));
    }

    @Operation(summary = "Remover produto do cardápio")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto removido com sucesso",
            content = @Content(schema = @Schema(implementation = MenuResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cardápio ou produto não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<MenuResponse> removeProduct(
        @PathVariable Long id,
        @PathVariable Long productId
                                                     ) {
        return ResponseEntity.ok(MenuResponse.fromModel(menuService.removeProduct(id, productId)));
    }

    @Operation(summary = "Excluir cardápio")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cardápio excluído com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Cardápio não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
