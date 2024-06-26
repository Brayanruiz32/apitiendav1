package com.principal.apitiendav1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

    List<Rol> findByDeletedAtNull();

}
