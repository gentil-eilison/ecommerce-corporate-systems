package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-28T15:49:44-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setId( categoria.getId() );
        categoriaResponseDTO.setNome( categoria.getNome() );
        categoriaResponseDTO.setDescricao( categoria.getDescricao() );

        return categoriaResponseDTO;
    }

    @Override
    public Categoria toEntity(CategoriaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setNome( dto.getNome() );
        categoria.setDescricao( dto.getDescricao() );

        return categoria;
    }

    @Override
    public List<CategoriaResponseDTO> toDTOList(List<Categoria> categorias) {
        if ( categorias == null ) {
            return null;
        }

        List<CategoriaResponseDTO> list = new ArrayList<CategoriaResponseDTO>( categorias.size() );
        for ( Categoria categoria : categorias ) {
            list.add( toResponseDTO( categoria ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDTO(CategoriaRequestDTO dto, Categoria categoria) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNome() != null ) {
            categoria.setNome( dto.getNome() );
        }
        if ( dto.getDescricao() != null ) {
            categoria.setDescricao( dto.getDescricao() );
        }
    }
}
