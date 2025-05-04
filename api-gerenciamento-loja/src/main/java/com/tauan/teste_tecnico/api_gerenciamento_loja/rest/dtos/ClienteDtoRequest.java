package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDtoRequest(
        String nome,
        @NotBlank(message = "Email é obrigatório.") @Email(message = "Digite um email válido.") String email,
        String cpf,
        @NotBlank(message = "A senha é obrigatoria")String senha
) {
    public ClienteModel toModel(){
        return new ClienteModel(null, nome.toUpperCase(), email, cpf.toUpperCase(), senha);
    }
}
