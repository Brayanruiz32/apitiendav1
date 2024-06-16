package com.principal.apitiendav1.services;

import java.util.List;

public interface IServices<T> {

    T encontrarRegistro(Long id);

    List<T> listarRegistros();
        
    T guardarRegistro(T nuevoRegistro);

    T actualizarRegistro(Long id, T datosRegistro);

    void eliminarRegistro();

}
