package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.categoria.CategoriaDTO;
import com.principal.apitiendav1.dto.categoria.CategoriaRequestDTO;
import com.principal.apitiendav1.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController implements IControllers<CategoriaDTO, CategoriaRequestDTO> {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/all")
    public List<CategoriaDTO> listar(){
        return categoriaService.listarRegistros();
    }

    @GetMapping("/all/available")
    public List<CategoriaDTO> listarDisponibles(){
        return categoriaService.listarRegistrosDisponibles();
    }

    @GetMapping("/find/{id}")
    public CategoriaDTO encontrar(@PathVariable Long id){
        return categoriaService.encontrarRegistro(id);
    }

    @PostMapping("/create")
    public CategoriaDTO crear(@RequestBody CategoriaRequestDTO categoriaNueva){
        return categoriaService.guardarRegistro(categoriaNueva);
    }

    @PutMapping("/update/{id}")
    public CategoriaDTO actualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaActualizar ){
        return categoriaService.actualizarRegistro(id, categoriaActualizar);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminar(@PathVariable Long id){
        categoriaService.eliminarRegistro(id);
    }

}
