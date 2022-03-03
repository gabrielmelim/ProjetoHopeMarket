package org.generation.app_hopemarket.repository;

import java.util.List;

import org.generation.app_hopemarket.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    
    public List<Carrinho> findAllByIdComprasContainingIgnoreCase(Long id_compras);
}
