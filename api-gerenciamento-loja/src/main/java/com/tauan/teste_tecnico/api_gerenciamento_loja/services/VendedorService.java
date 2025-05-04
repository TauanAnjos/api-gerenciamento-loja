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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;
    @Transactional
    public VendedorDtoResponse cadastrarVendedor(VendedorDtoRequest vendedorDtoRequest){
        if (vendedorRepository.existsByNome(vendedorDtoRequest.nome())){
            throw new DataConflictException("Vendedor já cadastrado");
        }
        String senha = encriptPassword(vendedorDtoRequest.senha());
        VendedorDtoRequest request = new VendedorDtoRequest(
                vendedorDtoRequest.nome(),
                vendedorDtoRequest.email(),
                senha
        );
        VendedorModel vendedorSalvo = vendedorRepository.save(request.toModel());

        return vendedorSalvo.toDtoResponse();
    }
    private String encriptPassword(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
    @Transactional
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
    @Transactional
    public VendedorDtoResponse atualizarVendedor(UUID id, VendedorDtoRequest vendedorDtoRequest){
        VendedorModel vendedorExist = vendedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));

        vendedorExist.updateFromDto(vendedorDtoRequest);

        VendedorModel vendedorAtualizado = vendedorRepository.save(vendedorExist);

        return vendedorAtualizado.toDtoResponse();
    }
    @Transactional
    public void deletarVendedor(UUID id){
        VendedorModel vendedorExist = vendedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado."));
        vendedorRepository.deleteById(id);
    }
}
