package org.generation.app_hopemarket.repository;

import java.util.List;

import org.generation.app_hopemarket.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> { 
    public List<Produto> findAllByCategoriaContainingIgnoreCase(String categoria);
}
