package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoResponse;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255, nullable = false)
    private String nome;
    @Column(length = 255,nullable = false,unique = true)
    private String email;
    @Column(length = 11,nullable = false,unique = true)
    private String cpf;
    @Column(nullable = false)
    private String senha;

    public ClienteModel() {
    }

    public ClienteModel(UUID id, String nome, String email, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ClienteDtoResponse toDtoResponse(){
        return new ClienteDtoResponse(this.id, this.nome,this.email,this.cpf);
    }
}
