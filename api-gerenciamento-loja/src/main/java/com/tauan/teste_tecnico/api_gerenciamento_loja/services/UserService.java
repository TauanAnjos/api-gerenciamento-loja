package com.tauan.teste_tecnico.api_gerenciamento_loja.services;

import com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions.BusinessRuleException;
import com.tauan.teste_tecnico.api_gerenciamento_loja.models.UserModel;
import com.tauan.teste_tecnico.api_gerenciamento_loja.repositories.UserRepository;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.UserDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserModel RegisterUser(UserModel userModel){
        if(repository.existsByEmail(userModel.getEmail())){
            throw new BusinessRuleException("Email já existe");
        }
        userModel.setSenha(new BCryptPasswordEncoder().encode(userModel.getPassword()));
        return repository.save(userModel);
    }

    public UserDtoResponse finByUserId(UUID userId){
        UserModel userExists = repository.findById(userId).orElseThrow(()-> new BusinessRuleException("Usuário não encontrado"));
        if(!userExists.getUser_id().equals(userId)){
            throw new BusinessRuleException("Esse usuário não está logado.");
        }
        return userExists.toDtoResponse();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
