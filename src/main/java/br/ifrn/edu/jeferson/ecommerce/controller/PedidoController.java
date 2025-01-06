package br.ifrn.edu.jeferson.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.AtualizarStatusPedidoDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salvar(
        @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO
    ) {
        var pedidoResponseDTO = pedidoService.salvar(pedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        var pedidoResponseDTO = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedidoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> listar(
        Pageable pageable,
        @RequestParam(required = false) StatusPedido statusPedido,
        @RequestParam(required = false) BigDecimal valorTotalMin,
        @RequestParam(required = false) BigDecimal valorTotalMax
    ) {
        Page<PedidoResponseDTO> pedidoResponseDTO = pedidoService.listar(
            pageable, 
            statusPedido, 
            valorTotalMin, 
            valorTotalMax
        );
        return ResponseEntity.ok(pedidoResponseDTO);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<PedidoResponseDTO>> listarPorClienteId(
        @PathVariable Long clienteId,
        Pageable pageable
    ) {
        Page<PedidoResponseDTO> pedidos = pedidoService.listarPorClienteId(pageable, clienteId);
        return ResponseEntity.ok(pedidos);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPedidoPorId(
        @PathVariable Long id,
        @Valid @RequestBody AtualizarStatusPedidoDTO atualizarStatusPedidoDTO
    ) {
        var pedidoResponseDTO = pedidoService.atualizarStatusPedidoPorId(id, atualizarStatusPedidoDTO);
        return ResponseEntity.ok(pedidoResponseDTO);
    }
}
