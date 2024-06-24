package com.principal.apitiendav1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.usuario.ResponseLogin;
import com.principal.apitiendav1.dto.usuario.UsuarioLoginDTO;
import com.principal.apitiendav1.services.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> loginUser(@RequestBody UsuarioLoginDTO userLogin){
        return new ResponseEntity<>(usuarioService.loginUser(userLogin), HttpStatus.OK);
    }

}
