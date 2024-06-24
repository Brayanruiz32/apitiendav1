package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioRequestDTO;
import com.principal.apitiendav1.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "CRUD USUARIO", description = "CRUD de usuarios")
public class UsuarioController implements IControllers<UsuarioDTO, UsuarioRequestDTO> {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/update/{id}")
    @Override
    public UsuarioDTO actualizar(Long id, UsuarioRequestDTO actualizarRegistro) {
        return usuarioService.actualizarRegistro(id, actualizarRegistro);
    }

    @PostMapping("/create")
    @Override
    public UsuarioDTO crear(UsuarioRequestDTO nuevoRegistro) {
        return usuarioService.guardarRegistro(nuevoRegistro);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void eliminar(Long id) {
        usuarioService.eliminarRegistro(id);
    }

    @GetMapping("/find/{id}")
    @Override
    public UsuarioDTO encontrar(Long id) {
        return usuarioService.encontrarRegistro(id);
    }

    @GetMapping("/all")
    @Override
    public List<UsuarioDTO> listar() {
        return usuarioService.listarRegistros();
    }

    @GetMapping("/all/available")
    @Override
    public List<UsuarioDTO> listarDisponibles() {
        return usuarioService.listarRegistrosDisponibles();
    }


}
