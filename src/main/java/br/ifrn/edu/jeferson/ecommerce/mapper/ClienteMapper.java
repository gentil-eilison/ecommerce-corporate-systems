package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    Cliente toEntity(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO toResponseDTO(Cliente cliente);
}
