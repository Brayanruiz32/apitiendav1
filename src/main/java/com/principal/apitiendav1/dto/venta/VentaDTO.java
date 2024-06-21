package com.principal.apitiendav1.dto.venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.producto.VentaProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.entities.EstadoVenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private Long id;
    private BigDecimal montoTotal;
    private UsuarioDTO usuarioDTO;
    private List<VentaProductoDTO> productosVentasDtos;
    private EstadoVenta estadoVenta;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
