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
public class ProdutoPartialRequestDTO {
    @Min(value = 0, message = "Estoque deve ser >= 0")
    @NotNull(message = "Estoque não pode ser nulo")
    private Integer estoque;
}
