package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

public record ItemVendaDtoResponse(
        String nomeProduto,
        int quantidade,
        double valorUnitario
) {}

