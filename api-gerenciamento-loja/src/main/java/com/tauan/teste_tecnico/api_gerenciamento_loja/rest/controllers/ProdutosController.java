package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;
    @Operation(
            summary = "Cadastrar novo Produto",
            description = "Endpoint para cadastrar um novo Produto no sistema. Requer o envio de dados válidos.",
            tags = {"Produto"}
    )
    @PostMapping
    public ResponseEntity<ProdutoDtoResponse> cadastrarProduto(@RequestBody ProdutoDtoRequest produtoDtoRequest){
        ProdutoDtoResponse response = produtosService.cadastrarProduto(produtoDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Buscar Produto",
            description = "Endpoint para Buscar um produto no sistema. Informando um dos campos a seguir, id ou nome.",
            tags = {"Produto"}
    )
    @GetMapping
    public ResponseEntity<ProdutoDtoResponse> buscarProduto(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false) String nome){
        ProdutoDtoResponse response = produtosService.buscarProduto(id, nome);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(
            summary = "Atualizar Produto",
            description = "Endpoint para atualizar um produto existente. Requer o envio de dados válidos.",
            tags = {"Produto"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDtoResponse> atualizarProduto(@PathVariable("id")UUID id, @RequestBody ProdutoDtoRequest produtoDtoRequest){
        ProdutoDtoResponse response = produtosService.atualizarProduto(id, produtoDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(
            summary = "Deletar Produto",
            description = "Endpoint para deletar um produto no sistema. Através do ID.",
            tags = {"Produto"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable("id") UUID id){
        produtosService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
}
