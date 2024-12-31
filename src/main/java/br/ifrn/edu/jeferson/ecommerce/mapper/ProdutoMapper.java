package br.ifrn.edu.jeferson.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);
    
    @Mapping(target = "categoriasIds", ignore = true)
    ProdutoResponseDTO toResponseDTO(Produto produto);
}
