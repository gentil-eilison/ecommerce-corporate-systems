package br.ifrn.edu.jeferson.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper {
    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);

    @Mapping(source = "cliente.id", target = "clienteId")
    EnderecoResponseDTO toResponseDTO(Endereco endereco);
}
