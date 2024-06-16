package com.principal.apitiendav1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.principal.apitiendav1.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByDeletedAtNull();



}
