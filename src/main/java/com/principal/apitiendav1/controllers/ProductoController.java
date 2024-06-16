package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.producto.ProductoRequestDTO;
import com.principal.apitiendav1.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController implements IControllers<ProductoDTO, ProductoRequestDTO> {

    @Autowired
    private ProductoService productoService;

    @PutMapping("/update/{id}")
    @Override
    public ProductoDTO actualizar(Long id, ProductoRequestDTO actualizarRegistro) {
        return productoService.actualizarRegistro(id, actualizarRegistro);
    }

    @PostMapping("/create")
    @Override
    public ProductoDTO crear(ProductoRequestDTO nuevoRegistro) {
        return productoService.guardarRegistro(nuevoRegistro);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void eliminar(Long id) {
        productoService.eliminarRegistro(id);
    }

    @GetMapping("/find/{id}")
    @Override
    public ProductoDTO encontrar(Long id) {
        return productoService.encontrarRegistro(id);
    }

    @GetMapping("/all")
    @Override
    public List<ProductoDTO> listar() {
        return productoService.listarRegistros();
    }

    @GetMapping("/all/available")
    @Override
    public List<ProductoDTO> listarDisponibles() {
        return productoService.listarRegistrosDisponibles();
    }

    

}
