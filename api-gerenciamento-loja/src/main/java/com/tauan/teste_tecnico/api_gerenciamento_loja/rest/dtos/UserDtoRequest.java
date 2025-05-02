package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDtoRequest(
        @NotBlank(message = "Email é obrigatório.")@Email(message = "Digite um email válido.") String email,
        @NotBlank(message = "A senha é obrigatoria") String senha)
{
    public UserModel toModel(){

        return new UserModel(null, this.email, this.senha);
    }
}
