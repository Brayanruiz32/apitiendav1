package com.principal.apitiendav1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
