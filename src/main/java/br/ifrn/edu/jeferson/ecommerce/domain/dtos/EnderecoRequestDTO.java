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
@Schema(description = "DTO usado para atualizar e criar um endereço")
public class EnderecoRequestDTO {
    @Schema(example = "Rua Centro")
    private String rua;

    @Schema(example = "56")
    private String numero;

    @Schema(example = "Centro")
    private String bairro;

    @Schema(example = "Recife")
    private String cidade;

    @Schema(example = "Pernambuco")
    private String estado;

    @Schema(example = "10000-000")
    private String cep;
}
