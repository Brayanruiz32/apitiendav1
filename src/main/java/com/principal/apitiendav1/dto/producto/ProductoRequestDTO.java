package com.principal.apitiendav1.dto.producto;

import com.principal.apitiendav1.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private String nombre;  
    private int stock;
    private String descripcion;
    private Categoria categoria;
}
