package com.tauan.teste_tecnico.api_gerenciamento_loja.models;

import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.UserDtoRequest;
import com.tauan.teste_tecnico.api_gerenciamento_loja.rest.dtos.UserDtoResponse;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_user")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255, nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;

    public UserModel() {
    }

    public UserModel(UUID user_id, String email, String senha) {
        this.id = user_id;
        this.email = email;
        this.senha = senha;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID user_id) {
        this.id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public UserDtoRequest toDTO(){
        return new UserDtoRequest(this.email, this.senha);
    }
    public UserDtoResponse toDtoResponsee(){
        return new UserDtoResponse(this.email);
    }
}
