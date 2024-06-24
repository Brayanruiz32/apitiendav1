package com.principal.apitiendav1.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioLoginDTO {

    private String usuario;
    private String contrasenia;


    

}
