package com.principal.apitiendav1.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.categoria.CategoriaDTO;
import com.principal.apitiendav1.dto.categoria.CategoriaRequestDTO;
import com.principal.apitiendav1.entities.Categoria;
import com.principal.apitiendav1.repositories.CategoriaRepository;

@Service
public class CategoriaService implements IServices<CategoriaDTO, CategoriaRequestDTO> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO actualizarRegistro(Long id, CategoriaDTO datosRegistro) {
        return null;
    }

    @Override
    public void eliminarRegistro() {
        
    }

    @Override
    public CategoriaDTO encontrarRegistro(Long id) {
        return null;
    }

    @Override
    public CategoriaDTO guardarRegistro(CategoriaRequestDTO nuevoRegistro) {
        Categoria nuevaCategoria = modelMapper.map(nuevoRegistro, Categoria.class);
        return modelMapper.map(categoriaRepository.save(nuevaCategoria), CategoriaDTO.class);
    }

    @Override
    public List<CategoriaDTO> listarRegistros() {
        return null;
    }

    
    

}
