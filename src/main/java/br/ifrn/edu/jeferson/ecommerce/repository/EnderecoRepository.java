package br.ifrn.edu.jeferson.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    boolean existsByClienteId(Long clienteId);
    Optional<Endereco> findByClienteId(Long clienteId);
}
