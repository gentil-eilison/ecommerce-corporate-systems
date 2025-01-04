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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {
    @Size(max = 255, message = "Nome deve ter até 255 caracteres")
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Size(max = 255, message = "Descrição deve ter até 255 caracteres")
    private String descricao;

    @DecimalMin(value = "0.0", message = "Preço deve ser >= 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "O preço deve ter até 2 casas decimais")
    @NotNull(message = "Preço não pode ser nulo")
    private BigDecimal preco;

    @Min(value = 0, message = "Estoque deve ser >= 0")
    @NotNull(message = "Estoque não pode ser nulo")
    private Integer estoque;

    @NotEmpty(message = "Deve ser passado pelo menos uma categoria")
    private List<Long> categoriasIds;
}
