package org.generation.app_hopemarket.repository;

import java.util.Optional;

import org.generation.app_hopemarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional <Usuario> findByEmail (String email);
}
