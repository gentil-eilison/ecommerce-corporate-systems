package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemPedidoMapper {
    List<ItemPedidoResponseDTO> toDTOList(List<ItemPedido> itemPedidos);
}
