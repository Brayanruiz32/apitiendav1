package com.principal.apitiendav1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByDeletedAtNull();

    Optional<Usuario> findByUsuario(String username);

}
