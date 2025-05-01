package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ProdutoDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.ProdutosService;
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

    @PostMapping
    public ResponseEntity<ProdutoDtoResponse> cadastrarProduto(@RequestBody ProdutoDtoRequest produtoDtoRequest){
        ProdutoDtoResponse response = produtosService.cadastrarProduto(produtoDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ProdutoDtoResponse> buscarProduto(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false) String nome){
        ProdutoDtoResponse response = produtosService.buscarProduto(id, nome);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDtoResponse> atualizarProduto(@PathVariable("id")UUID id, @RequestBody ProdutoDtoRequest produtoDtoRequest){
        ProdutoDtoResponse response = produtosService.atualizarProduto(id, produtoDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable("id") UUID id){
        produtosService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
}
