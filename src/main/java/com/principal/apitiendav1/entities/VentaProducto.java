package com.principal.apitiendav1.entities;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table(name = "venta_productos")
public class VentaProducto {
    
    @EmbeddedId
    private VentaProductoId id = new VentaProductoId();

    private int cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;

    @ManyToOne
    @MapsId("ventaId")
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Override
    public String toString() {
        return "VentaProducto [id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", subtotal=" + subtotal
                +"]";
    }

    

}
