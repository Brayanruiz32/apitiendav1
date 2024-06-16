package com.principal.apitiendav1.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.categoria.CategoriaDTO;
import com.principal.apitiendav1.dto.categoria.CategoriaRequestDTO;
import com.principal.apitiendav1.entities.Categoria;
import com.principal.apitiendav1.repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements IServices<CategoriaDTO, CategoriaRequestDTO> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO actualizarRegistro(Long id, CategoriaRequestDTO datosRegistro) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encuentra el registro a actualizar"));
        categoriaEncontrada.setNombre(datosRegistro.getNombre());
        Categoria categoriaActualizada = categoriaRepository.save(categoriaEncontrada);
        return modelMapper.map(categoriaActualizada, CategoriaDTO.class);
    }

    @Override
    public void eliminarRegistro(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No existe el registro"));
        categoriaEncontrada.setDeletedAt(LocalDateTime.now());
        categoriaRepository.save(categoriaEncontrada);
    }

    @Override
    public CategoriaDTO encontrarRegistro(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No existe el registro"));
        return modelMapper.map(categoriaEncontrada, CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO guardarRegistro(CategoriaRequestDTO nuevoRegistro) {
        Categoria nuevaCategoria = modelMapper.map(nuevoRegistro, Categoria.class);
        return modelMapper.map(categoriaRepository.save(nuevaCategoria), CategoriaDTO.class);
    }

    @Override
    public List<CategoriaDTO> listarRegistros() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(c -> modelMapper.map(c, CategoriaDTO.class)).toList();
        return categoriasDTO;
    }

    @Override
    public List<CategoriaDTO> listarRegistrosDisponibles() {
       List<Categoria> categorias = categoriaRepository.findByDeletedAtNull();
       List<CategoriaDTO> categoriasDTO = categorias.stream().map(c -> modelMapper.map(c, CategoriaDTO.class)).toList();
       return categoriasDTO;
    }

    
    
    

}
