package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VendaDtoResponse(
        UUID id,
        LocalDateTime dataVenda,
        String nomeCliente,
        String nomeVendedor,
        List<ItemVendaDtoResponse> itens,
        double valorTotal
) {}
