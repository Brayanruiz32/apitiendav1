package com.principal.apitiendav1.services;

import java.util.List;

import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioRequestDTO;

public class UsuarioService implements IServices<UsuarioDTO, UsuarioRequestDTO>  {

    @Override
    public UsuarioDTO actualizarRegistro(Long id, UsuarioRequestDTO datosRegistro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminarRegistro(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public UsuarioDTO encontrarRegistro(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UsuarioDTO guardarRegistro(UsuarioRequestDTO nuevoRegistro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsuarioDTO> listarRegistros() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsuarioDTO> listarRegistrosDisponibles() {
        // TODO Auto-generated method stub
        return null;
    }

    

}
