package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

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
}
