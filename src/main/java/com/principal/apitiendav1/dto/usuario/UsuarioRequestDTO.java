package com.principal.apitiendav1.dto.usuario;

import com.principal.apitiendav1.dto.rol.RolDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    private String usuario;
    private String contrasenia;
    private RolDTO rol;
}
