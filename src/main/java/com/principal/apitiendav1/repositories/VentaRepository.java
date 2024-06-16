package com.principal.apitiendav1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
