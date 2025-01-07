package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO usado para representar um produto na listagem deles por categoria")
public class ProdutosPorCategoriaResponseDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Playstation 5")
    private String nome;

    @Schema(example = "Um console de vídeo-game")
    private String descricao;
    
    @Schema(example = "399.99")
    private BigDecimal preco;

    @Schema(example = "99")
    private Integer estoque;
}
