package com.principal.apitiendav1.services;

import java.util.List;

import org.modelmapper.ModelMapper;

public interface IServices<T, E> {

    ModelMapper modelMapper = new ModelMapper();

    T encontrarRegistro(Long id);

    List<T> listarRegistros();

    List<T> listarRegistrosDisponibles();
        
    T guardarRegistro(E nuevoRegistro);

    T actualizarRegistro(Long id, E datosRegistro);

    void eliminarRegistro(Long id);

}
