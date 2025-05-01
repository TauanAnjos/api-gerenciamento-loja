package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ProdutosModel;

import java.time.LocalDateTime;

public record ProdutoDtoRequest(
        String nome,
        Integer estoque,
        Double valor
) {
    public ProdutosModel toModel() {
        return new ProdutosModel(
                null,
                this.nome().toUpperCase(),
                LocalDateTime.now(),
                this.estoque(),
                this.valor()
        );
    }

}
