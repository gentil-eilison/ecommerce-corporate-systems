package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    Cliente toEntity(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO toResponseDTO(Cliente cliente);
    List<ClienteResponseDTO> toListDTO(List<Cliente> clientes);
    default Page<ClienteResponseDTO> toPageDTO(Page<Cliente> clientes) {
        return clientes.map(this::toResponseDTO);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    void updateEntityFromDTO(ClienteRequestDTO clienteRequestDTO, @MappingTarget Cliente cliente);
}
