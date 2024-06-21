package com.principal.apitiendav1.dto.producto;

import java.math.BigDecimal;

import com.principal.apitiendav1.entities.VentaProductoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaProductoDTO {
    private VentaProductoId ventaProductoId;
    private int cantidad;
    private ProductoDTO producto;
}
