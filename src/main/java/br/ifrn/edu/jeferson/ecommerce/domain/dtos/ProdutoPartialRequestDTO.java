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
@Schema(description = "DTO usado para atualizar o estoque de um produto")
public class ProdutoPartialRequestDTO {
    @Schema(example = "250")
    @Min(value = 0, message = "Estoque deve ser >= 0")
    @NotNull(message = "Estoque não pode ser nulo")
    private Integer estoque;
}
