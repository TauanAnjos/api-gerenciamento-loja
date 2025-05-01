package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;

public record ClienteDtoRequest(
        String nome,
        String email,
        String cpf,
        String senha
) {
    public ClienteModel toModel(){
        return new ClienteModel(null, nome.toUpperCase(), email.toUpperCase(), cpf.toUpperCase(), senha);
    }
}
