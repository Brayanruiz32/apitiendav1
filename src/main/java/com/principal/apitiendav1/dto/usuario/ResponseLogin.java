package com.principal.apitiendav1.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLogin {

    private String usuario; 
    private String contrasenia;
    private String token;

}
