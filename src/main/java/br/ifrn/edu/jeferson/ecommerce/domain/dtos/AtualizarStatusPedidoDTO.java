package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO usado para atualizar o status do pedido")
public class AtualizarStatusPedidoDTO {
    @Schema(example = "PAGO")
    @NotNull(message = "O status do pedido deve ser fornecido")
    private StatusPedido statusPedido;
}
