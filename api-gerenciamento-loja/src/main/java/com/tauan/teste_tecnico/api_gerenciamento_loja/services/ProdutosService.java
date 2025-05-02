package com.tauan.teste_tecnico.api_gerenciamento_loja.services;

import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.BusinessRuleException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.NotFoundException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ProdutosModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.repositories.ProdutosRepository;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Transactional
    public ProdutoDtoResponse cadastrarProduto(ProdutoDtoRequest produtoDtoRequest){
        Optional<ProdutosModel> produtoExistente = produtosRepository.findByNome(produtoDtoRequest.nome().toUpperCase());

        if (produtoExistente.isPresent()){
            ProdutosModel produto = produtoExistente.get();
            produto.setEstoque(produto.getEstoque() + produtoDtoRequest.estoque());
            produto.setValor(produtoDtoRequest.valor());
            produtosRepository.save(produto);
            return produto.toDtoResponse();
        }
        ProdutosModel produtosModel = produtoDtoRequest.toModel();
        ProdutosModel produtoSalvo = produtosRepository.save(produtosModel);
        return produtoSalvo.toDtoResponse();
    }
    @Transactional
    public ProdutoDtoResponse buscarProduto(UUID id, String nome){
        Optional<ProdutosModel> produtoExist = Optional.empty();

        if (id != null){
            produtoExist = produtosRepository.findById(id);
        } else if (nome != null) {
            produtoExist = produtosRepository.findByNome(nome.toUpperCase());
        }
        else {
            throw new BusinessRuleException("Informe pelo menos um parâmetro: id ou nome");
        }
        return produtoExist.map(ProdutosModel::toDtoResponse).orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }
    @Transactional
    public ProdutoDtoResponse atualizarProduto(UUID id, ProdutoDtoRequest produtoDtoRequest){
        ProdutosModel produtoExist = produtosRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        produtoExist.updateFromDto(produtoDtoRequest);

        ProdutosModel produtoAtualizado = produtosRepository.save(produtoExist);
        return produtoAtualizado.toDtoResponse();
    }
    @Transactional
    public void deletarProduto(UUID id){
        ProdutosModel produtoExist = produtosRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        produtosRepository.deleteById(id);
    }
}
