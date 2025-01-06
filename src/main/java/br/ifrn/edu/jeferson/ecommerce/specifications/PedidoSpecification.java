package br.ifrn.edu.jeferson.ecommerce.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;

import java.math.BigDecimal;

public class PedidoSpecification {
    public static Specification<Pedido> comStatusPedido(StatusPedido statusPedido) {
        return (root, query, cb) -> 
            statusPedido == null ? null : cb.equal(root.get("statusPedido"), statusPedido);
    }

    public static Specification<Pedido> comValorTotalMin(BigDecimal valorTotalMin) {
        return (root, query, cb) ->
            valorTotalMin == null ? null : cb.greaterThanOrEqualTo(root.get("valorTotal"), valorTotalMin);
    }

    public static Specification<Pedido> comValorTotalMax(BigDecimal valorTotalMax) {
        return (root, query, cb) ->
            valorTotalMax == null ? null : cb.lessThanOrEqualTo(root.get("valorTotal"), valorTotalMax);
    }
}
