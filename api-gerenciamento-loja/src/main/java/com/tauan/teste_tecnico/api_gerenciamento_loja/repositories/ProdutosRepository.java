package com.tauan.teste_tecnico.api_gerenciamento_loja.repositories;

import com.tauan.teste_tecnico.api_gerenciamento_loja.models.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosModel, UUID> {
}
