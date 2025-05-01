package com.tauan.teste_tecnico.api_gerenciamento_loja.services;

import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.BusinessRuleException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.DataConflictException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.NotFoundException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.repositories.ClienteRepository;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.ClienteDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDtoResponse cadastrarCliente(ClienteDtoRequest clienteDtoRequest){
        if(clienteRepository.existsByCpf(clienteDtoRequest.cpf())){
            throw new DataConflictException("CPF já cadastrado.");
        }
        ClienteModel clienteSalvo = clienteRepository.save(clienteDtoRequest.toModel());

        return clienteSalvo.toDtoResponse();
    }

    public ClienteDtoResponse buscarCliente(UUID id, String email, String cpf){
        Optional<ClienteModel> clienteExist = Optional.empty();

        if (id != null){
            clienteExist = clienteRepository.findById(id);
        } else if (email != null) {
            clienteExist = clienteRepository.findByEmail(email.toUpperCase());
        } else if (cpf != null) {
            clienteExist = clienteRepository.findByCpf(cpf);
        }
        else {
            throw new BusinessRuleException("Informe pelo menos um parâmetro: id, email ou cpf.");
        }
        return clienteExist.map(ClienteModel::toDtoResponse).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public ClienteDtoResponse atualizarCliente(UUID id, ClienteDtoRequest clienteDtoRequest){
        ClienteModel clienteExist = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));

        clienteExist.updateFromDto(clienteDtoRequest);

        ClienteModel clienteAtualizado = clienteRepository.save(clienteExist);
        return clienteAtualizado.toDtoResponse();
    }

    public void deletarCliente(UUID id){
        ClienteModel clienteExist = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }
}
