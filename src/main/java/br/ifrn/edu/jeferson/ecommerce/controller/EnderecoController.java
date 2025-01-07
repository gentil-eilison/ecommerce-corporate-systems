package br.ifrn.edu.jeferson.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes/{clienteId}/enderecos")
@Tag(name = "Endereços")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Cria um endereço de um cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnderecoResponseDTO> salvar(
        @PathVariable Long clienteId,
        @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(enderecoService.salvar(enderecoRequestDTO, clienteId));
    }

    @Operation(summary = "Buscar um endereço de um cliente")
    @GetMapping
    public ResponseEntity<EnderecoResponseDTO> buscarPorClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(enderecoService.buscarPorClienteId(clienteId));
    }

    @Operation(summary = "Deleta o endereço de um cliente")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarPorClienteId(@PathVariable Long clienteId) {
        enderecoService.deletarPorClienteId(clienteId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o endereço de um cliente")
    @PutMapping
    public ResponseEntity<EnderecoResponseDTO> atualizarPorClienteId(
        @PathVariable Long clienteId,
        @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO
    ) {
        return ResponseEntity.ok(enderecoService.atualizarPorClienteId(clienteId, enderecoRequestDTO));
    }
}
