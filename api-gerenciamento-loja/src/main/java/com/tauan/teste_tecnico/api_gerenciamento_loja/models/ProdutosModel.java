package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_produto")
public class ProdutosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(name = "data_criacao_cadastro", nullable = false)
    private LocalDateTime dataCriacaoCadastro;
    @Column(nullable = false)
    private Integer estoque;
    @Column(nullable = false)
    private Double valor;

    public ProdutosModel() {
    }

    public ProdutosModel(UUID id, String nome, LocalDateTime dataCriacaoCadastro, Integer estoque, Double valor) {
        this.id = id;
        this.nome = nome;
        this.dataCriacaoCadastro = dataCriacaoCadastro;
        this.estoque = estoque;
        this.valor = valor;
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

    public LocalDateTime getDataCriacaoCadastro() {
        return dataCriacaoCadastro;
    }

    public void setDataCriacaoCadastro(LocalDateTime dataCriacaoCadastro) {
        this.dataCriacaoCadastro = dataCriacaoCadastro;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    @PrePersist
    public void prePersist() {
        this.dataCriacaoCadastro = LocalDateTime.now();
    }
    public ProdutoDtoResponse toDtoResponse() {
        return new ProdutoDtoResponse(
                this.id,
                this.nome,
                this.dataCriacaoCadastro,
                this.estoque,
                this.valor
        );
    }
    public void updateFromDto(ProdutoDtoRequest dto){
        this.nome = dto.nome().toUpperCase();
        this.estoque = dto.estoque();
        this.valor = dto.valor();
    }

}
