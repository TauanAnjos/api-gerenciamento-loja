package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import java.util.UUID;

public record VendedorDtoResponse(
        UUID id,
        String nome,
        String email
) {
}
