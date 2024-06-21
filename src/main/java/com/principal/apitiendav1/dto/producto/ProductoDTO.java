package com.principal.apitiendav1.dto.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.principal.apitiendav1.dto.categoria.CategoriaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private int stock;
    private String descripcion;
    private BigDecimal precio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private CategoriaDTO categoria;
}
