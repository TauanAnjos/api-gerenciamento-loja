package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.VendedorModel;

public record VendedorDtoRequest(
        String nome,
        String email,
        String senha
) {
    public VendedorModel toModel(){
        return new VendedorModel(null, nome, email, senha);
    }
}
