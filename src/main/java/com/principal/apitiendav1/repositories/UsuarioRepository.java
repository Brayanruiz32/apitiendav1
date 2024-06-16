package com.principal.apitiendav1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
