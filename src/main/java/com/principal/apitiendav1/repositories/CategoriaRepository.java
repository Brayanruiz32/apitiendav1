package com.principal.apitiendav1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
