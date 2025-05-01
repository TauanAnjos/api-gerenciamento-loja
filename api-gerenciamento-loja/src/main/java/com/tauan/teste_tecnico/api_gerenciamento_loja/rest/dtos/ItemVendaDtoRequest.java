package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ItemVendaDtoRequest(
        @NotNull(message = "ID do produto é obrigatório.")
        UUID produtoId,

        @Min(value = 1, message = "Quantidade deve ser pelo menos 1.")
        int quantidade
) {
}
