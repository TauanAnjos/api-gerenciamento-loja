package com.tauan.teste_tecnico.api_gerenciamento_loja.repositories;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.VendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel, UUID> {
}
