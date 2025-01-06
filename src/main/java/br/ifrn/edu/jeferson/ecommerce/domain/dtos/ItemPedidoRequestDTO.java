package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

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
public class ItemPedidoRequestDTO {
    @NotNull(message = "O id do produto não pode ser vazio")
    Long produtoId;

    @NotNull(message = "A quantidade do produto não pode ser vazia")
    @Min(value = 1, message = "A quantidade do produto tem que ser >= 1")
    Integer quantidade;
}
