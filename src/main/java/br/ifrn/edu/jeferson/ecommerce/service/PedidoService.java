package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ItemPedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specifications.PedidoSpecification;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    private void jogarSeNaoHaEstoqueSuficiente(Produto produto, Integer quantidade) {
        if (produto.getEstoque() < quantidade) {
            throw new BusinessException(
                String.format("O produto de id %d não possui estoque suficiente", produto.getId())  
            );
        }
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {
        var produtosIds = pedidoRequestDTO.getProdutosIds();
        var produtos = produtoRepository.findAllById(produtosIds);

        if (produtosIds.size() != produtos.size()) {
            throw new BusinessException("Um ou mais produtos informados não existem");
        }

        var cliente = clienteRepository
                        .findById(pedidoRequestDTO.getClienteId())
                        .orElseThrow(() -> 
                            new ResourceNotFoundException(
                                String.format("Não há cliente com id %d", pedidoRequestDTO.getClienteId())
                            )
                        );

        var pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
        pedido.setCliente(cliente);

        var itensPedido = new ArrayList<ItemPedido>();
        var produtosParaAtualizar = new ArrayList<Produto>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (var itemDTO : pedidoRequestDTO.getItens()) {
            var produto = produtos.stream()
                .filter(p -> p.getId().equals(itemDTO.getProdutoId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(
                    String.format("Produto com id %d não encontrado", itemDTO.getProdutoId())
                ));
            
            jogarSeNaoHaEstoqueSuficiente(produto, itemDTO.getQuantidade());
            
            var itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setPedido(pedido);

            itensPedido.add(itemPedido);
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));

            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtosParaAtualizar.add(produto);
        }

        pedido.setValorTotal(valorTotal);
        pedido.setItens(itensPedido);

        pedido = pedidoRepository.save(pedido);
        produtoRepository.saveAll(produtos);
        itemPedidoRepository.saveAll(itensPedido);
        return pedidoMapper.toResponseDTO(pedido);
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        var pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("Não existe pedido com esse id", id)
            ));
        return pedidoMapper.toResponseDTO(pedido);
    }

    public Page<PedidoResponseDTO> listar(
        Pageable pageable,
        StatusPedido statusPedido,
        BigDecimal valorTotalMin,
        BigDecimal valorTotalMax
    ) {
        Specification<Pedido> spec = Specification.where(PedidoSpecification.comStatusPedido(statusPedido))
                                                    .and(PedidoSpecification.comValorTotalMin(valorTotalMin))
                                                    .and(PedidoSpecification.comValorTotalMax(valorTotalMax));
        Page<Pedido> pedidos = pedidoRepository.findAll(spec, pageable);
        return pedidoMapper.toPageDTO(pedidos);
    }

    public Page<PedidoResponseDTO> listarPorClienteId(Pageable pageable, Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException(
                String.format("Cliente com id %d não foi encontrado", clienteId)
            );
        }
        Page<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId, pageable);
        return pedidoMapper.toPageDTO(pedidos);
    }
}
