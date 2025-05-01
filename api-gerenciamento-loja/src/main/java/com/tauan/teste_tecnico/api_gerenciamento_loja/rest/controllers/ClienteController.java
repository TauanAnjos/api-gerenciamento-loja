package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Operation(
            summary = "Cadastrar novo Cliente",
            description = "Endpoint para cadastrar um novo cliente no sistema. Requer o envio de dados válidos.",
            tags = {"Cliente"}
    )
    @PostMapping
    public ResponseEntity<ClienteDtoResponse> cadastrarCliente(@RequestBody @Valid ClienteDtoRequest clienteDtoRequest){
        ClienteDtoResponse response = clienteService.cadastrarCliente(clienteDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Buscar Cliente",
            description = "Endpoint para Buscar um cliente no sistema. Informando um dos campos a seguir id, email ou cpf.",
            tags = {"Cliente"}
    )
    @GetMapping
    public ResponseEntity<ClienteDtoResponse> buscarCliente(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String cpf){
        ClienteDtoResponse cliente = clienteService.buscarCliente(id, email, cpf);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
    @Operation(
            summary = "Atualizar Cliente",
            description = "Endpoint para atualizar um cliente existente. Requer o envio de dados válidos.",
            tags = {"Cliente"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDtoResponse> atualizarCliente(@PathVariable("id")UUID id,@RequestBody @Valid ClienteDtoRequest clienteDtoRequest){
        ClienteDtoResponse response = clienteService.atualizarCliente(id, clienteDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(
            summary = "Deletar Cliente",
            description = "Endpoint para deletar um cliente no sistema. Através do ID.",
            tags = {"Cliente"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable("id")UUID id){
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
    }
}
