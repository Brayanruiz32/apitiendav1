package com.principal.apitiendav1.dto.venta;

import java.util.List;

import com.principal.apitiendav1.dto.producto.VentaProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.entities.EstadoVenta;
import com.principal.apitiendav1.entities.VentaProductoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequestDTO {
    //propiedades para crear la venta
    // private BigDecimal montoTotal;
    
    private UsuarioDTO usuarioDTO;
    private EstadoVenta estadoVenta;
    //propiedades para crear el Venta_Producto
    private List<VentaProductoDTO> productosVentasDtos;
}
