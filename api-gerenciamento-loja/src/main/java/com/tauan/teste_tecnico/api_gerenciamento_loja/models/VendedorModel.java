package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoResponse;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_vendedor")
public class VendedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255, nullable = false)
    private String nome;
    @Column(length = 255,nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;

    public VendedorModel() {
    }

    public VendedorModel(UUID id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public VendedorDtoResponse toDtoResponse(){
        return new VendedorDtoResponse(this.nome,this.email);
    }
}
