package com.principal.apitiendav1.dto.usuario;

import java.time.LocalDateTime;

import com.principal.apitiendav1.dto.rol.RolDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String usuario;
    private String contrasenia;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAd;
    private RolDTO rol;


}
