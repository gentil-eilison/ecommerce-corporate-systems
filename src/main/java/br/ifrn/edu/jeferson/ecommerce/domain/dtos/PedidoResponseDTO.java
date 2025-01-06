package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private StatusPedido statusPedido;
    private Long clienteId;
    private List<ItemPedidoResponseDTO> itens;
}
