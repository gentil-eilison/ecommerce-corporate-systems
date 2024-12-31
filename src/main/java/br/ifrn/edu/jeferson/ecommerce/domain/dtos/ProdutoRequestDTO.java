package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import jakarta.validation.constraints.DecimalMin;
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
    @NotBlank(message = "Nome deve ser informado")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 255, message = "Descrição teve ter no máximo 255 caracteres")
    private String descricao;

    @NotNull(message = "Preço deve ser informado")
    @DecimalMin(value = "0.0", message = "Valor mínimo é de 0.0")
    private BigDecimal preco;

    @Min(value = 0, message = "Valor mínimo é 0")
    private Integer estoque = 0;

    @NotEmpty(message = "Deve ser passado ao menos uma categoria")
    private List<Long> categoriasIds;
}
