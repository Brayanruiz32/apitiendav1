package com.principal.apitiendav1.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IControllers<T, E> {

    List<T> listar();

    List<T> listarDisponibles();

    T encontrar(@PathVariable Long id);

    T crear(@RequestBody E nuevoRegistro);

    T actualizar(@PathVariable Long id, @RequestBody E actualizarRegistro);

    void eliminar(@PathVariable Long id);
}
