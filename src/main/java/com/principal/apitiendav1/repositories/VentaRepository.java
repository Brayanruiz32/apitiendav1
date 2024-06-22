package com.principal.apitiendav1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.principal.apitiendav1.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByDeletedAtNull();


    // @Query("SELECT v FROM Venta v RIGHT JOIN v.productos p")
    // List<Venta> findAllVentaProducto();

    // @Query("SELECT v FROM Venta v JOIN v.usuario u")
    // List<Venta> encuentraTodasVentas();

}
