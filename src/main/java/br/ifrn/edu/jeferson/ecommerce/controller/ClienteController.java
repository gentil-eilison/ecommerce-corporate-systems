package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Retorna um cliente específico")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        var clienteResponseDTO = clienteService.buscarPorId(id);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @Operation(summary = "Lista todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> buscarTodos(Pageable pageable) {
        var clientePageResponseDTO = clienteService.buscarTodos(pageable);
        return ResponseEntity.ok(clientePageResponseDTO);
    }

    @Operation(summary = "Cria um cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDTO> salvar(
        @Valid @RequestBody ClienteRequestDTO clienteRequestDTO
    ) {
        var clienteResponseDTO = clienteService.salvar(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
    }

    @Operation(summary = "Deleta um cliente específico")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um cliente específico")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
        @PathVariable Long id, 
        @Valid @RequestBody ClienteRequestDTO clienteRequestDTO
    ) {
        var clienteResponseDTO = clienteService.atualizar(clienteRequestDTO, id);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @Operation(summary = "Retorna a lista de pedidos do cliente")
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<Page<PedidoResponseDTO>> listarPedidosPorClienteId(
        @PathVariable Long id,
        Pageable pageable
    ) {
        Page<PedidoResponseDTO> pedidos = clienteService.listarProdutosPorClienteId(id, pageable);
        return ResponseEntity.ok(pedidos);
    }
}
