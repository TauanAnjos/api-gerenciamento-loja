package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoResponse;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
public class ClienteModel extends UserModel{
    @Column(length = 11,nullable = false,unique = true)
    private String cpf;

    public ClienteModel() {
    }

    public ClienteModel(UUID id, String nome, String email, String cpf, String senha) {
        setId(id);
        setNome(nome);
        setEmail(email);
        this.cpf = cpf;
        setSenha(senha);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ClienteDtoResponse toDtoResponse(){
        return new ClienteDtoResponse(getId(), getNome(),getEmail(),this.cpf);
    }
    public void updateFromDto(ClienteDtoRequest dto){
        setNome(dto.nome().toUpperCase());
        setEmail(dto.email());
        this.cpf = dto.cpf();
        setSenha(dto.senha());
    }
}
