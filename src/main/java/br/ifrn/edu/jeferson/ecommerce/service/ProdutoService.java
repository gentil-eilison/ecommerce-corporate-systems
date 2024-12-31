package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        List<Long> categoriasIds = produtoRequestDTO.getCategoriasIds();
        var categorias = categoriaRepository
            .findAllById(categoriasIds);
        
        if (categorias.size() != categoriasIds.size()) {
            throw new BusinessException("Uma ou mais categorias informadas não existem");
        }

        var produto = produtoMapper.toEntity(produtoRequestDTO);
        produto.setCategorias(categorias);
        produtoRepository.save(produto);
        var produtoResponseDTO = produtoMapper.toResponseDTO(produto);
        produtoResponseDTO.setCategoriasIds(
            categorias.stream()
                .map(Categoria::getId)
                .collect(Collectors.toList())
            );
        return produtoResponseDTO;
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        var produto = produtoRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Produto com esse id não existe"));
        var produtoResponseDTO = produtoMapper.toResponseDTO(produto);
        produtoResponseDTO.setCategoriasIds(
            produto.getCategorias().stream()
                .map(Categoria::getId)
                .collect(Collectors.toList())
        );
        return produtoResponseDTO;
    }
}
