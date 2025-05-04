package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoResponse;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_vendedor")
public class VendedorModel extends UserModel{


    public VendedorModel() {
    }

    public VendedorModel(UUID id, String nome, String email, String senha) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public VendedorDtoResponse toDtoResponse(){
        return new VendedorDtoResponse(getId(), getNome(), getEmail());
    }

    public void updateFromDto(VendedorDtoRequest dto){
        setNome(dto.nome().toUpperCase());
        setEmail(dto.email());
        setSenha(dto.senha());
    }
}
