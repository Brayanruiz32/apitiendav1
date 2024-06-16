package com.principal.apitiendav1.dto.rol;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<Permiso> permisos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
