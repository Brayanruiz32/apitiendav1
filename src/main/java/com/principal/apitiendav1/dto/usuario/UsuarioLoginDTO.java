package com.principal.apitiendav1.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioLoginDTO {

    private String usuario;
    private String contrasenia;

}
