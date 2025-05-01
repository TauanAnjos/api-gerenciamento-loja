package com.tauan.teste_tecnico.api_gerenciamento_loja.rest.controllers;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoResponse;
import com.tauan.teste_tecnico.api_gerenciamento_loja.services.VendedorService;
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

    @PostMapping
    public ResponseEntity<VendedorDtoResponse> cadastrarVendedor(@RequestBody VendedorDtoRequest vendedorDtoRequest){
        VendedorDtoResponse response = vendedorService.cadastrarVendedor(vendedorDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<VendedorDtoResponse> buscarVendedor(
            @RequestParam(required = false)UUID id,
            @RequestParam(required = false) String nome){
        VendedorDtoResponse response = vendedorService.buscarVendedor(id, nome);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDtoResponse> atualizarVendedor(@PathVariable("id")UUID id, VendedorDtoRequest vendedorDtoRequest){
        VendedorDtoResponse response = vendedorService.atualizarVendedor(id, vendedorDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVendedor(@PathVariable("id")UUID id){
        vendedorService.deletarVendedor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Vendedor deletado com sucesso.");
    }
}
