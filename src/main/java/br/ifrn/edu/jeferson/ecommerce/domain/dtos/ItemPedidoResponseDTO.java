package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa cada item de um pedido")
public class ItemPedidoResponseDTO {
    private ItemPedidoProdutoResponseDTO produto;

    @Schema(example = "20")
    private Integer quantidade;
}
