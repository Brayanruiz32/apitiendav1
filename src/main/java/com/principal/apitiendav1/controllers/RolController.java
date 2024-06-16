package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public RolDTO actualizar(Long id, RolRequestDTO actualizarRegistro) {
        return null;
    }

    @Override
    public RolDTO crear(RolRequestDTO nuevoRegistro) {
        return rolService.guardarRegistro(nuevoRegistro);
    }

    @Override
    public void eliminar(Long id) {
        
    }

    @Override
    public RolDTO encontrar(Long id) {
        return rolService.encontrarRegistro(id);
    }

    @Override
    public List<RolDTO> listar() {
        return rolService.listarRegistros();
    }

    @Override
    public List<RolDTO> listarDisponibles() {
        return rolService.listarRegistrosDisponibles();
    }

    



}
