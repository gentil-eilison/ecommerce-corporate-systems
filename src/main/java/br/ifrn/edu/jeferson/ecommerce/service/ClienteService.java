package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    private void jogarSeCpfNaoUnico(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new BusinessException(
                    String.format("Cliente com CPF %s já existe", cpf)
            );
        }
    }

    private void jogarSeEmailNaoUnico(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new BusinessException(
                    String.format("Cliente com e-mail %s já existe", email)
            );
        }
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        var cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cliente com id %d não encontrado", id)
                ));
        return clienteMapper.toResponseDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        jogarSeCpfNaoUnico(clienteRequestDTO.getCpf());
        jogarSeEmailNaoUnico(clienteRequestDTO.getEmail());

        Cliente cliente = clienteMapper.toEntity(clienteRequestDTO);
        clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
    }

    public void deletarPorId(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException(
                String.format("Cliente com id %d não existe", id)
            );
        }
        clienteRepository.deleteById(id);
    }
}
