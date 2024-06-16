package com.principal.apitiendav1.dto.rol;

import java.util.Set;

import com.principal.apitiendav1.entities.Permiso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequestDTO {
    
    private String nombre;
    private Set<Permiso> permisos;
}
 