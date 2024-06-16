package com.principal.apitiendav1.services;

import java.util.List;

import com.principal.apitiendav1.entities.Usuario;

public class UsuarioService implements IServices<Usuario> {

    @Override
    public List<Usuario> listarRegistros() {
        return null;
    }
    
    @Override
    public Usuario encontrarRegistro(Long id) {
        return null;
    }

    @Override
    public Usuario guardarRegistro(Usuario nuevoRegistro) {
        return null;
    }

    @Override
    public Usuario actualizarRegistro(Long id, Usuario datosRegistro) {
        return null;
    }

    @Override
    public void eliminarRegistro() {
        
    }
}
