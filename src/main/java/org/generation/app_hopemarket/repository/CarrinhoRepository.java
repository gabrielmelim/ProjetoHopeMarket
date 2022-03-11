package org.generation.app_hopemarket.repository;

import org.generation.app_hopemarket.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    public List<Carrinho> findAllByEmailContainingIgnoreCase (String email);

}
