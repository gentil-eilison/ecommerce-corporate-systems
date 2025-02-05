package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO usado para criar e atualizar clientes")
public class ClienteRequestDTO {
    @Schema(example = "João Alberto")
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 255, message = "Nome deve ter até 256 caracteres")
    private String nome;

    @Schema(example = "joao.al@gmail.com")
    @Email(message = "Email deve seguir o formato de emails")
    @NotBlank(message = "Email não pode ser vazio")
    @Size(max = 255, message = "Email deve possuir até 256 caracteres")
    private String email;

    @Schema(example = "123.456.789-10")
    @NotBlank(message = "CPF não pode ser nulo")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve seguir o formato XXX.XXX.XXX-XX")
    private String cpf;

    @Schema(example = "(11) 91111-1111")
    @NotBlank(message = "Telefone não pode ser nulo")
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "Telefone deve seguir o formato (XX) XXXXX-XXXX")
    private String telefone;
}
