package com.principal.apitiendav1.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.producto.VentaProductoDTO;
import com.principal.apitiendav1.entities.Producto;
import com.principal.apitiendav1.entities.Venta;
import com.principal.apitiendav1.entities.VentaProducto;
import com.principal.apitiendav1.repositories.ProductoRepository;
import com.principal.apitiendav1.repositories.VentaProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaProductoRepository ventaProductoRepository;


    public List<VentaProducto> crearVentasProductos(List<VentaProductoDTO> lista, Venta venta){

        List<VentaProducto> ventasProductos = new ArrayList<>();

         for (VentaProductoDTO ventaProducto : lista) {
            Producto producto = productoRepository.findById(ventaProducto.getProducto().getId()).orElseThrow(() -> new EntityNotFoundException());
            int cantidad = ventaProducto.getCantidad();
            BigDecimal precio = producto.getPrecio();
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
            
            //setemos el nuevoventaproducto
            VentaProducto nuevoVentaProducto = new VentaProducto();
            nuevoVentaProducto.setCantidad(cantidad);
            nuevoVentaProducto.setPrecio(precio);
            nuevoVentaProducto.setSubtotal(subtotal);
            nuevoVentaProducto.setVenta(venta);
            nuevoVentaProducto.setProducto(producto);
            ventasProductos.add(nuevoVentaProducto);
        }
        return ventasProductos;

    }




}
