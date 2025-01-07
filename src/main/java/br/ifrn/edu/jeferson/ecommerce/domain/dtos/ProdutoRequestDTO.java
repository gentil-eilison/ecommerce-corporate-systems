package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de criação de produto")
public class ProdutoRequestDTO {

    @Schema(description = "Nome do produto", example = "Playstation 5")
    @Size(max = 255, message = "Nome deve ter até 255 caracteres")
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Schema(description = "Descriçaõ breve do produto", example = "Um console de vídeo-game")
    @Size(max = 255, message = "Descrição deve ter até 255 caracteres")
    private String descricao;

    @Schema(description = "O preço unitário do produto", example = "399.99")
    @DecimalMin(value = "0.0", message = "Preço deve ser >= 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "O preço deve ter até 2 casas decimais")
    @NotNull(message = "Preço não pode ser nulo")
    private BigDecimal preco;

    @Schema(description = "A quantidade disponível do produto", example = "10")
    @Min(value = 0, message = "Estoque deve ser >= 0")
    @NotNull(message = "Estoque não pode ser nulo")
    private Integer estoque;

    @Schema(description = "Os ids das categorias a qual esse produto pertence", example = "[1, 2, 3]")
    @NotEmpty(message = "Deve ser passado pelo menos uma categoria")
    private List<Long> categoriasIds;
}
