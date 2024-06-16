package com.principal.apitiendav1.dto.rol;

import com.principal.apitiendav1.entities.Permiso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {

    private Long id;
    private String nombre; 
    private Permiso permiso;

}
