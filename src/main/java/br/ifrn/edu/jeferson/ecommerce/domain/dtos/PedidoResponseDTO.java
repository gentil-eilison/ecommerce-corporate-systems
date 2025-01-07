package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa um pedido")
public class PedidoResponseDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "2025-01-06T17:29:49.888807")
    private LocalDateTime dataPedido;

    @Schema(example = "299.99")
    private BigDecimal valorTotal;

    @Schema(example = "AGUARDANDO")
    private StatusPedido statusPedido;

    @Schema(example = "10")
    private Long clienteId;
    
    private List<ItemPedidoResponseDTO> itens;
}
