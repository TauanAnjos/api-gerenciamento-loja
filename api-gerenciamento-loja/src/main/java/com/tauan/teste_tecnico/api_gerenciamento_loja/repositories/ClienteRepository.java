package com.tauan.teste_tecnico.api_gerenciamento_loja.repositories;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
    Optional<ClienteModel> findByCpf(String cpf);


    Optional<ClienteModel> findByEmail(String email);

    boolean existsByCpf(String cpf);
}
