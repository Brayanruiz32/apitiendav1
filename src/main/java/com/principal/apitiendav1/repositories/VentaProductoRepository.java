package com.principal.apitiendav1.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.apitiendav1.entities.VentaProducto;
import com.principal.apitiendav1.entities.VentaProductoId;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoId> {


    List<VentaProducto> findByIdVentaId(Long id);

    

}
