package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.ClienteService;
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

    @PostMapping
    public ResponseEntity<ClienteDtoResponse> cadastrarCliente(@RequestBody ClienteDtoRequest clienteDtoRequest){
        ClienteDtoResponse response = clienteService.cadastrarCliente(clienteDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ClienteDtoResponse> buscarCliente(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String cpf){
        ClienteDtoResponse cliente = clienteService.buscarCliente(id, email, cpf);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDtoResponse> atualizarCliente(@PathVariable("id")UUID id, ClienteDtoRequest clienteDtoRequest){
        ClienteDtoResponse response = clienteService.atualziarCliente(id, clienteDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable("id")UUID id){
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente deletado com sucesso.");
    }
}
