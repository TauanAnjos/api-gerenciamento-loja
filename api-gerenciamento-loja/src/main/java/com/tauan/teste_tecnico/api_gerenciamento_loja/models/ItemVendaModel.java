package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_itemvenda")
public class ItemVendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private ProdutosModel produto;

    private int quantidade;

    public ItemVendaModel() {
    }

    public ItemVendaModel(UUID id, ProdutosModel produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProdutosModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutosModel produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
