package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO usado para criar um pedido")
public class PedidoRequestDTO {
    @Schema(description = "O id do cliente a qual o pedido pertence", example = "10")
    @NotNull(message = "O id do cliente não pode ser vazio")
    Long clienteId;

    @NotEmpty(message = "O pedido deve ter pelo menos um produto e sua quantidade")
    @Valid
    List<ItemPedidoRequestDTO> itens;

    @Schema(hidden = true)
    public List<Long> getProdutosIds() {
        return itens.stream().map(itemPedido -> itemPedido.getProdutoId()).toList();
    }
}
