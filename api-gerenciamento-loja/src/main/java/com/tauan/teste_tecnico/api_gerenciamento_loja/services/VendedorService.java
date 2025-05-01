package com.tauan.teste_tecnico.api_gerenciamento_loja.services;

import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.BusinessRuleException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.DataConflictException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.NotFoundException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.models.VendedorModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.repositories.VendedorRepository;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.VendedorDtoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorDtoResponse cadastrarVendedor(VendedorDtoRequest vendedorDtoRequest){
        if (vendedorRepository.existsByNome(vendedorDtoRequest.nome())){
            throw new DataConflictException("Vendedor já cadastrado");
        }
        VendedorModel vendedorSalvo = vendedorRepository.save(vendedorDtoRequest.toModel());

        return vendedorSalvo.toDtoResponse();
    }

    public VendedorDtoResponse buscarVendedor(UUID id, String nome){
        Optional<VendedorModel> vendedorExist = Optional.empty();

        if (id != null){
            vendedorExist = vendedorRepository.findById(id);
        } else if (nome != null) {
            vendedorExist = vendedorRepository.findByNome(nome.toUpperCase());
        }
        else {
            throw new BusinessRuleException("Informe pelo menos um parâmetro: id ou nome");
        }

        return vendedorExist.map(VendedorModel::toDtoResponse).orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));
    }

    public VendedorDtoResponse atualizarVendedor(UUID id, VendedorDtoRequest vendedorDtoRequest){
        VendedorModel vendedorExist = vendedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));

        vendedorExist.updateFromDto(vendedorDtoRequest);

        VendedorModel vendedorAtualizado = vendedorRepository.save(vendedorExist);

        return vendedorAtualizado.toDtoResponse();
    }

    public void deletarVendedor(UUID id){
        VendedorModel vendedorExist = vendedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));
        vendedorRepository.deleteById(id);
    }
}
