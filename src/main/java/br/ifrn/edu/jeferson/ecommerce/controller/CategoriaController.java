package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "API de gerenciamento de categorias dos Produtos")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Cria uma nova categoria")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoriaResponseDTO> salvar(
        @Valid @RequestBody CategoriaRequestDTO categoriaDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvar(categoriaDto));
    }

    @Operation(summary = "Lista todas as categorias")
    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.listar(pageable));
    }

    @Operation(summary = "Deleta uma nova categoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza uma nova categoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody CategoriaRequestDTO categoriaDto
    ) {
        return ResponseEntity.ok(categoriaService.atualizar(id, categoriaDto));
    }

    @Operation(summary = "Associa um produto a uma categoria")
    @PostMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> associarProdutoCategoria(
        @PathVariable Long categoriaId,
        @PathVariable Long produtoId
    ) {
        categoriaService.associarProdutoCategoria(categoriaId, produtoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Desassocia um produto a uma categoria")
    @DeleteMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProdutoCategoria(
        @PathVariable Long categoriaId,
        @PathVariable Long produtoId
    ) {
        categoriaService.removerProdutoCategoria(categoriaId, produtoId);
        return ResponseEntity.ok().build();
    }

}
