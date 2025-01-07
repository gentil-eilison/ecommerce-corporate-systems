package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.CategoriaMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    private void jogarSeNomeCategoriaNaoUnico(String nome, Long id) {
        boolean categoriaExiste = false;

        if (id != null) {
            categoriaExiste = categoriaRepository.existsByNomeAndIdNot(nome, id);
        } else {
            categoriaExiste = categoriaRepository.existsByNome(nome);
        }

        if (categoriaExiste) throw new BusinessException("Já existe uma categoria com esse nome");
    }

    public CategoriaResponseDTO salvar(CategoriaRequestDTO categoriaDto) {
        var categoria = categoriaMapper.toEntity(categoriaDto);

        jogarSeNomeCategoriaNaoUnico(categoriaDto.getNome(), null);

        categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(categoria);
    }

    public Page<CategoriaResponseDTO> listar(Pageable pageable){
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return categoriaMapper.toPageDTO(categorias);
    }

    public void deletar(Long id) {
        var categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        if (categoria.getProdutos().size() > 0) {
            throw new BusinessException("Essa categoria possui produtos associados");
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaDto) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Categoria não encontrada"));

        jogarSeNomeCategoriaNaoUnico(categoriaDto.getNome(), id);

        categoriaMapper.updateEntityFromDTO(categoriaDto, categoria);
        var categoriaAlterada = categoriaRepository.save(categoria);

        return categoriaMapper.toResponseDTO(categoriaAlterada);
    }

    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Categoria não encontrada"));
        return categoriaMapper.toResponseDTO(categoria);
    }

    public void associarProdutoCategoria(Long categoriaId, Long produtoId) {
        var produto = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Não há produto com o id especificado"
            ));
        var categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Não há categoria com o id especificado"
            ));
        
        if (produto.getCategorias().contains(categoria)) {
            throw new BusinessException("Esse produto já possui essa categoria");
        }
        produto.getCategorias().add(categoria);
        produtoRepository.save(produto);
    }

}
