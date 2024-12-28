package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    private void jogarSeCpfNaoUnico(String cpf, Long id) {
        boolean exists = false;

        if (id != null) {
            exists = clienteRepository.existsByCpfAndIdNot(cpf, id);
        }
        exists = clienteRepository.existsByCpf(cpf);

        if (exists) throw new BusinessException(
            String.format("Cliente com CPF %s já existe", cpf)
        );
    }

    private void jogarSeEmailNaoUnico(String email, Long id) {
        boolean exists = false;
        
        if (id != null) {
            exists = clienteRepository.existsByEmailAndIdNot(email, id);
        }
        exists = clienteRepository.existsByEmail(email);

        if (exists) throw new BusinessException(
            String.format("Cliente com e-mail %s já existe", email)
        );
    }

    private void validarCliente(ClienteRequestDTO clienteRequestDTO, Long id) {
        jogarSeCpfNaoUnico(clienteRequestDTO.getCpf(), id);
        jogarSeEmailNaoUnico(clienteRequestDTO.getEmail(), id);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        var cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cliente com id %d não encontrado", id)
                ));
        return clienteMapper.toResponseDTO(cliente);
    }

    public Page<ClienteResponseDTO> buscarTodos(Pageable pageable) {
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return clienteMapper.toPageDTO(clientes);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        validarCliente(clienteRequestDTO, null);

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

    public ClienteResponseDTO atualizar(ClienteRequestDTO clienteRequestDTO, Long id) {
        var cliente = clienteRepository
            .findById(id)
            .orElseThrow(() ->  new ResourceNotFoundException(
                String.format("Cliente com id %d não foi encontrado", id)
            ));
        validarCliente(clienteRequestDTO, id);
        
        clienteMapper.updateEntityFromDTO(clienteRequestDTO, cliente);
        var clienteAtualizado = clienteRepository.save(cliente);
        
        return clienteMapper.toResponseDTO(clienteAtualizado);
    }
}
