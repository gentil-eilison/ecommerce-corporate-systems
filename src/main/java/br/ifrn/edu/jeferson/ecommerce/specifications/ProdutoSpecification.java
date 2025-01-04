package br.ifrn.edu.jeferson.ecommerce.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;

public class ProdutoSpecification {
    public static Specification<Produto> comPrecoMin(BigDecimal precoMin) {
        return (root, query, cb) -> 
            precoMin == null ? null : cb.greaterThanOrEqualTo(root.get("preco"), precoMin);
    }

    public static Specification<Produto> comNomeContendo(String nome) {
        return (root, query, cb) ->
            nome == null ? null : cb.like(root.get("nome"), "%" + nome + "%");
    }
}
