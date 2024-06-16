package com.principal.apitiendav1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.principal.apitiendav1.entities.Categoria;
import com.principal.apitiendav1.repositories.CategoriaRepository;

public class CategoriaService implements IServices<Categoria> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarRegistros() {
        
        return null;
    }

    @Override
    public Categoria encontrarRegistro(Long id) {
        return null;
    }

    @Override
    public Categoria guardarRegistro(Categoria nuevoRegistro) {
        return null;
    }


    @Override
    public Categoria actualizarRegistro(Long id, Categoria datosRegistro) {
        return null;
    }

    @Override
    public void eliminarRegistro() {
        
    }
    

}
