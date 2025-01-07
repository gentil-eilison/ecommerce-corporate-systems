package br.ifrn.edu.jeferson.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutosPorCategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API de gerenciamento de produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    
    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(
        @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(produtoService.salvar(produtoRequestDTO));
    }

    @Operation(summary = "Retorna um produto específico")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Retorna uma lista de todos os produtos")
    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> buscarTodos(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) BigDecimal precoMin,
        Pageable pageable
    ) {
        return ResponseEntity.ok(produtoService.buscarTodos(pageable, nome, precoMin));
    }

    @Operation(summary = "Deleta um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarPorId(
        @PathVariable Long id,
        @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO
    ) {
        var produtoResponseDTO = produtoService.atualizarPorId(produtoRequestDTO, id);
        return ResponseEntity.ok(produtoResponseDTO);
    }

    @Operation(summary = "Retorna os produtos de uma categoria específica")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<ProdutosPorCategoriaResponseDTO>> produtosPorCategoria(
        @PathVariable Long categoriaId,
        Pageable pageable
    ) {
        var produtosPorCategoriaResponseDTO = produtoService.produtosPorCategoria(categoriaId, pageable);
        return ResponseEntity.ok(produtosPorCategoriaResponseDTO);
    }

    @Operation(summary = "Atualiza o estoque de um produto específico")
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<ProdutoResponseDTO> atualizarEstoque(
        @Valid @RequestBody ProdutoPartialRequestDTO produtoPartialRequestDTO,
        @PathVariable Long id
    ) {
        var produtoResponseDTO = produtoService.atualizarEstoque(id, produtoPartialRequestDTO);
        return ResponseEntity.ok(produtoResponseDTO);
    }
}
