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
@Schema(description = "DTO usado para representar um cliente")
public class ClienteResponseDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "João Alberto")
    private String nome;

    @Schema(example = "joao.al@gmail.com")
    private String email;

    @Schema(example = "123.456.789-10")
    private String cpf;

    @Schema(example = "(11) 91111-1111")
    private String telefone;
}
