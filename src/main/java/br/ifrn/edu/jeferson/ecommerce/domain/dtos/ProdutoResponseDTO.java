package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de resposta de um produto")
public class ProdutoResponseDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Playstation 5")
    private String nome;

    @Schema(example = "Um console de vídeo-game")
    private String descricao;

    @Schema(example = "399.99")
    private BigDecimal preco;

    @Schema(example = "90")
    private Integer estoque;
    private List<CategoriaResponseDTO> categorias;
}
