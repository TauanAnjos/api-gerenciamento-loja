package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record VendaDtoRequest(
        @NotNull(message = "ID do cliente é obrigatório.")
        UUID clienteId,

        @NotNull(message = "ID do vendedor é obrigatório.")
        UUID vendedorId,

        @NotNull(message = "A lista de itens não pode ser nula.")
        List<ItemVendaDtoRequest> itens
) {}
