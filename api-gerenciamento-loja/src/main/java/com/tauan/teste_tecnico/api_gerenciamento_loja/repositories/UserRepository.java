package com.tauan.teste_tecnico.api_gerenciamento_loja.repositories;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
}
