package com.tauan.teste_tecnico.api_gerenciamento_loja.services;

import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.BusinessRuleException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.NotFoundException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.models.*;
import com.tauan.teste_tecnico.api_gerenciamento_loja.repositories.*;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VendaService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private VendaRepository vendaRepository;

    @Transactional
    public VendaDtoResponse gerarVenda(VendaDtoRequest vendaDtoRequestdto) {
        ClienteModel cliente = clienteRepository.findById(vendaDtoRequestdto.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado."));

        VendedorModel vendedor = vendedorRepository.findById(vendaDtoRequestdto.vendedorId())
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));

        List<ItemVendaModel> itensVenda = new ArrayList<>();
        for (ItemVendaDtoRequest itemDto : vendaDtoRequestdto.itens()) {
            ProdutosModel produto = produtosRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

            if (produto.getEstoque() < itemDto.quantidade()) {
                throw new BusinessRuleException("Estoque insuficiente para o produto: " + produto.getNome());
            }
            produto.setEstoque(produto.getEstoque() - itemDto.quantidade());
            produtosRepository.save(produto);
            ItemVendaModel itemVenda = new ItemVendaModel();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDto.quantidade());
            itensVenda.add(itemVenda);
        }
        VendaModel venda = new VendaModel();
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);
        venda.setDataVenda(LocalDateTime.now());
        venda.setItens(itensVenda);
        vendaRepository.save(venda);
        List<ItemVendaDtoResponse> itens = venda.getItens().stream().map(item ->
                new ItemVendaDtoResponse(
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getProduto().getValor()
                )
        ).toList();
        double valorTotal = itens.stream()
                .mapToDouble(item -> item.quantidade() * item.valorUnitario())
                .sum();
        return new VendaDtoResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getCliente().getNome(),
                venda.getVendedor().getNome(),
                itens,
                valorTotal
        );
    }
    public VendaDtoResponse buscarVendaPorId(UUID id) {
        VendaModel venda = vendaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada."));

        List<ItemVendaDtoResponse> itens = venda.getItens().stream().map(item ->
                new ItemVendaDtoResponse(
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getProduto().getValor()
                )
        ).toList();
        double valorTotal = itens.stream()
                .mapToDouble(item -> item.quantidade() * item.valorUnitario())
                .sum();
        return new VendaDtoResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getCliente().getNome(),
                venda.getVendedor().getNome(),
                itens,
                valorTotal
        );
    }
}
