package com.principal.apitiendav1.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreacionDTO {

    private Long id;
    private String nombre;  
    private int stock;
    private String descripcion;



}
