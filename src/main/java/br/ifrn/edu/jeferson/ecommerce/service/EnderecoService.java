package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    private void jogarSeClienteJaTiverEndereco(Long clienteId) {
        if (enderecoRepository.existsByClienteId(clienteId)) {
            throw new BusinessException("Esse cliente já possui um endereço");
        }
    }

    public EnderecoResponseDTO salvar(EnderecoRequestDTO enderecoRequestDTO, Long clienteId) {
        jogarSeClienteJaTiverEndereco(clienteId);
        var cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        var endereco = enderecoMapper.toEntity(enderecoRequestDTO);
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO buscarPorClienteId(Long clienteId) {
        var endereco = enderecoRepository.findByClienteId(clienteId).orElseThrow(() -> new BusinessException("Não há endereço para esse cliente"));
        return enderecoMapper.toResponseDTO(endereco);
    }

    public void deletarPorClienteId(Long clienteId) {
        var endereco = enderecoRepository.findByClienteId(clienteId).orElseThrow(() -> new BusinessException("Não há endereço para esse cliente"));
        enderecoRepository.delete(endereco);
    }

    public EnderecoResponseDTO atualizarPorClienteId(Long clienteId, EnderecoRequestDTO enderecoRequestDTO) {
        var endereco = enderecoRepository.findByClienteId(clienteId).orElseThrow(() -> new BusinessException("Não há endereço cadastrado para esse cliente"));
        enderecoMapper.updateEntityFromDTO(enderecoRequestDTO, endereco);
        var enderecoAtualizado = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(enderecoAtualizado);
    }
}
