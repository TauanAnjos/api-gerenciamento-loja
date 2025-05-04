package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.VendedorModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VendedorDtoRequest(
        String nome,
        @NotBlank(message = "Email é obrigatório.") @Email(message = "Digite um email válido.")String email,
        @NotBlank(message = "A senha é obrigatoria")String senha
) {
    public VendedorModel toModel(){
        return new VendedorModel(null, nome.toUpperCase(), email, senha);
    }
}
