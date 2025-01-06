package br.ifrn.edu.jeferson.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
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
}
