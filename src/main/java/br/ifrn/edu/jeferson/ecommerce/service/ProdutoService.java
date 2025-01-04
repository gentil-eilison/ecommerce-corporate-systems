package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutosPorCategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specifications.ProdutoSpecification;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private List<Categoria> buscarCategorias(List<Long> categoriasIds) {
        var categorias = categoriaRepository.findAllById(categoriasIds);
        if (categorias.size() != categoriasIds.size()) {
            throw new BusinessException("Há uma ou mais categorias inválidas");
        }
        return categorias;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        var categorias = buscarCategorias(produtoRequestDTO.getCategoriasIds());
        var produto = produtoMapper.toEntity(produtoRequestDTO);
        produto.setCategorias(categorias);
        produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
            String.format("Produto com id %d não existe", id)
        ));
        return produtoMapper.toResponseDTO(produto);
    }

    public Page<ProdutoResponseDTO> buscarTodos(Pageable pageable, String nome, BigDecimal precoMin) {
        Specification<Produto> spec = Specification.where(ProdutoSpecification.comPrecoMin(precoMin))
                                                    .and(ProdutoSpecification.comNomeContendo(nome));
        Page<Produto> produtos = produtoRepository.findAll(spec, pageable);
        return produtoMapper.toPageDTO(produtos);
    }

    public void deletarPorId(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                String.format("Não há produto com id %d", id)
            );
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizarPorId(ProdutoRequestDTO produtoRequestDTO, Long id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Produto com id %d não encontrado", id)
            )
        );
        var categorias = buscarCategorias(produtoRequestDTO.getCategoriasIds());
        produto.setCategorias(categorias);
        produtoMapper.updateEntityFromDTO(produtoRequestDTO, produto);
        var produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAtualizado);
    }

    public Page<ProdutosPorCategoriaResponseDTO> produtosPorCategoria(
        Long categoriaId,
        Pageable pageable
    ) {
        var categoria = categoriaRepository.findById(categoriaId)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Categoria com id %d não encontrada", categoriaId)
                            ));
        var produtosCategoria = categoria.getProdutos();
        var produtosPaginados = new PageImpl<Produto>(produtosCategoria, pageable, produtosCategoria.size());
        return produtoMapper.toProdutosPorCategoriaPageDTO(produtosPaginados);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, ProdutoPartialRequestDTO produtoPartialRequestDTO) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Produto com id %d não encontrado", id)
            )
        );
        produtoMapper.partialUpdateEntityFromDTO(produtoPartialRequestDTO, produto);
        var produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAtualizado);
    }
}
