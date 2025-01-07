package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO usado para representar os itens na criação de um pedido")
public class ItemPedidoRequestDTO {
    @Schema(description = "O id do produto", example = "20")
    @NotNull(message = "O id do produto não pode ser vazio")
    Long produtoId;

    @Schema(description = "A quantidade do produto especificado pelo id do produto", example = "20")
    @NotNull(message = "A quantidade do produto não pode ser vazia")
    @Min(value = 1, message = "A quantidade do produto tem que ser >= 1")
    Integer quantidade;
}
