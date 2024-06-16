package com.principal.apitiendav1.services;

import java.util.List;

import org.modelmapper.ModelMapper;

public interface IServices<T, E> {

    ModelMapper modelMapper = new ModelMapper();

    T encontrarRegistro(Long id);

    List<T> listarRegistros();
        
    T guardarRegistro(E nuevoRegistro);

    T actualizarRegistro(Long id, T datosRegistro);

    void eliminarRegistro();

}
