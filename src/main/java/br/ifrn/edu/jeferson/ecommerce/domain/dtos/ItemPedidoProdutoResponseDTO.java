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
@Schema(description = "DTO que representa o produto dentro de um pedido")
public class ItemPedidoProdutoResponseDTO {
    @Schema(example = "10")
    private Long id;

    @Schema(example = "Smartwatch GalaxyWatch")
    private String nome;
}
