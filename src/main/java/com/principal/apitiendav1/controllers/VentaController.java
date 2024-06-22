package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.venta.VentaDTO;
import com.principal.apitiendav1.dto.venta.VentaRequestDTO;
import com.principal.apitiendav1.services.VentaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/venta")
@Tag(name = "CRUD VENTA", description = "CRUD de ventas")
public class VentaController implements IControllers<VentaDTO, VentaRequestDTO> {

    @Autowired
    private VentaService ventaService;

    @PutMapping("/update/{id}")
    @Override
    public VentaDTO actualizar(Long id, VentaRequestDTO actualizarRegistro) {
        return ventaService.actualizarRegistro(id, actualizarRegistro);
    }

    @PostMapping("/create")
    @Override
    public VentaDTO crear(VentaRequestDTO nuevoRegistro) {
        return ventaService.guardarRegistro(nuevoRegistro);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void eliminar(Long id) {
        ventaService.eliminarRegistro(id);
    }

    @GetMapping("/find/{id}")
    @Override
    public VentaDTO encontrar(Long id) {
        return ventaService.encontrarRegistro(id);
    }

    @GetMapping("/all")
    @Override
    public List<VentaDTO> listar() {
        return ventaService.listarRegistros();
    }

    @GetMapping("/all/available")
    @Override
    public List<VentaDTO> listarDisponibles() {
        return ventaService.listarRegistrosDisponibles();
    }

    



}
