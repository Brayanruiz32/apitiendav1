package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.rol.RolDTO;
import com.principal.apitiendav1.dto.rol.RolRequestDTO;
import com.principal.apitiendav1.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolController implements IControllers<RolDTO, RolRequestDTO> {

    @Autowired
    private RolService rolService;

    @PutMapping("/update/{id}")
    @Override
    public RolDTO actualizar(Long id, RolRequestDTO actualizarRegistro) {
        return rolService.actualizarRegistro(id, actualizarRegistro);
    }

    @PostMapping("/create")
    @Override
    public RolDTO crear(RolRequestDTO nuevoRegistro) {
        return rolService.guardarRegistro(nuevoRegistro);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void eliminar(Long id) {
        rolService.eliminarRegistro(id);
    }
    @GetMapping("/find/{id}")
    @Override
    public RolDTO encontrar(Long id) {
        return rolService.encontrarRegistro(id);
    }
    @GetMapping("/all")
    @Override
    public List<RolDTO> listar() {
        return rolService.listarRegistros();
    }
    @GetMapping("/all/available")
    @Override
    public List<RolDTO> listarDisponibles() {
        return rolService.listarRegistrosDisponibles();
    }

    



}
