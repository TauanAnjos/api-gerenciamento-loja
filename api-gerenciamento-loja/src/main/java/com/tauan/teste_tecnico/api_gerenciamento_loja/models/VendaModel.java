package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_venda")
public class VendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataVenda;

    @ManyToOne
    private ClienteModel cliente;

    @ManyToOne
    private VendedorModel vendedor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemVendaModel> itens;

    public VendaModel() {
    }

    public VendaModel(UUID id, LocalDateTime dataVenda, ClienteModel cliente, VendedorModel vendedor, List<ItemVendaModel> itens) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.itens = itens;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public VendedorModel getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorModel vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItemVendaModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaModel> itens) {
        this.itens = itens;
    }
}
