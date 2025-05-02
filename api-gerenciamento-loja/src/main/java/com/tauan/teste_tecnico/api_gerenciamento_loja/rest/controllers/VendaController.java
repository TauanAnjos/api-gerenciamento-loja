package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendaDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendaDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Operation(
            summary = "Cadastrar nova Venda",
            description = "Endpoint para cadastrar uma nova venda no sistema. Requer o envio dos dados válidos, incluindo os itens da venda.",
            tags = {"Venda"}
    )
    @PostMapping
    public ResponseEntity<String> gerarVenda(@RequestBody @Valid VendaDtoRequest vendaDtoRequestdto) {
        vendaService.gerarVenda(vendaDtoRequestdto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venda registrada com sucesso.");
    }
    @Operation(
            summary = "Buscar Venda",
            description = "Endpoint para buscar uma venda no sistema. O ID da venda deve ser informado.",
            tags = {"Venda"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<VendaDtoResponse> buscarVenda(@PathVariable UUID id) {
        VendaDtoResponse venda = vendaService.buscarVendaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(venda);
    }

}
