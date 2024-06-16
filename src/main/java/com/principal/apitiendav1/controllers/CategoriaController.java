package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.apitiendav1.dto.categoria.CategoriaDTO;
import com.principal.apitiendav1.dto.categoria.CategoriaRequestDTO;
import com.principal.apitiendav1.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listar(){
        return categoriaService.listarRegistros();
    }


    @PostMapping("/create")
    public CategoriaDTO crear(@RequestBody CategoriaRequestDTO categoriaNueva){
        return categoriaService.guardarRegistro(categoriaNueva);
    }

}
