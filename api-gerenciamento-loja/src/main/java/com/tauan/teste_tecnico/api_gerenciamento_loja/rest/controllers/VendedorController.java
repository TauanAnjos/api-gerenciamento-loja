package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;
    @Operation(
            summary = "Cadastrar novo Vendedor",
            description = "Endpoint para cadastrar um novo vendedor no sistema. Requer o envio de dados válidos.",
            tags = {"Vendedor"}
    )
    @PostMapping
    public ResponseEntity<VendedorDtoResponse> cadastrarVendedor(@RequestBody @Valid VendedorDtoRequest vendedorDtoRequest){
        VendedorDtoResponse response = vendedorService.cadastrarVendedor(vendedorDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Buscar Vendedor",
            description = "Endpoint para Buscar um vendedor no sistema. Informando um dos campos a seguir, id ou nome.",
            tags = {"Vendedor"}
    )
    @GetMapping
    public ResponseEntity<VendedorDtoResponse> buscarVendedor(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false) String nome){
        VendedorDtoResponse response = vendedorService.buscarVendedor(id, nome);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(
            summary = "Atualizar Vendedor",
            description = "Endpoint para atualizar um vendedor existente. Requer o envio de dados válidos.",
            tags = {"Vendedor"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<VendedorDtoResponse> atualizarVendedor(@PathVariable("id")UUID id,@RequestBody @Valid VendedorDtoRequest vendedorDtoRequest){
        VendedorDtoResponse response = vendedorService.atualizarVendedor(id, vendedorDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(
            summary = "Deletar Vendedor",
            description = "Endpoint para deletar um vendedor no sistema. Através do ID.",
            tags = {"Vendedor"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVendedor(@PathVariable("id")UUID id){
        vendedorService.deletarVendedor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Vendedor deletado com sucesso.");
    }
}
