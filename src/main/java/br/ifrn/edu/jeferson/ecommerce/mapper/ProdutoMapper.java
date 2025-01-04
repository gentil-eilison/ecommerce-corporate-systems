package br.ifrn.edu.jeferson.ecommerce.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoPartialRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutosPorCategoriaResponseDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING, 
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProdutoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);
    
    ProdutoResponseDTO toResponseDTO(Produto produto);
    ProdutosPorCategoriaResponseDTO toProdutosPorCategoriaResponseDTO(Produto produto);

    default Page<ProdutoResponseDTO> toPageDTO(Page<Produto> produtos) {
        return produtos.map(this::toResponseDTO);
    }
    default Page<ProdutosPorCategoriaResponseDTO> toProdutosPorCategoriaPageDTO(Page<Produto> produtos) {
        return produtos.map(this::toProdutosPorCategoriaResponseDTO);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    void updateEntityFromDTO(ProdutoRequestDTO produtoRequestDTO, @MappingTarget Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdateEntityFromDTO(
        ProdutoPartialRequestDTO produtoPartialRequestDTO,
        @MappingTarget Produto produto
    );
}
