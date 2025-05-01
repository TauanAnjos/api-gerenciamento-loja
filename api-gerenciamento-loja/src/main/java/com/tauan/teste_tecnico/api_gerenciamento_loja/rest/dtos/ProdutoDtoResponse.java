package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoDtoResponse(
        UUID id,
        String nome,
        LocalDateTime dataCriacaoCadastro,
        Integer estoque,
        Double valor
) {
}
