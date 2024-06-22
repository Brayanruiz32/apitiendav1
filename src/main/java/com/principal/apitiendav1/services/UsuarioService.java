package com.principal.apitiendav1.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioRequestDTO;
import com.principal.apitiendav1.entities.Rol;
import com.principal.apitiendav1.entities.Usuario;
import com.principal.apitiendav1.repositories.RolRepository;
import com.principal.apitiendav1.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class UsuarioService implements IServices<UsuarioDTO, UsuarioRequestDTO>  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO actualizarRegistro(Long id, UsuarioRequestDTO datosRegistro) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        usuario.setUsuario(datosRegistro.getUsuario());
        usuario.setContrasenia(passwordEncoder.encode(datosRegistro.getContrasenia()));
        //Setear el rol 
        if (datosRegistro.getRol() == null) {
            throw new IllegalArgumentException("No puede ser nulo el Rol");
        }
        Rol rol = rolRepository.findById(datosRegistro.getRol().getId()).orElseThrow(()-> new EntityNotFoundException());
        usuario.setRol(rol);
        UsuarioDTO usuarioDTO = modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
        return usuarioDTO;
    }

    @Override
    public void eliminarRegistro(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        usuario.setDeletedAt(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO encontrarRegistro(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO guardarRegistro(UsuarioRequestDTO nuevoRegistro) {
        Usuario nuevoUsuario = modelMapper.map(nuevoRegistro, Usuario.class);
        nuevoUsuario.setContrasenia(passwordEncoder.encode(nuevoRegistro.getContrasenia()));
        UsuarioDTO usuarioDTO = modelMapper.map(usuarioRepository.save(nuevoUsuario), UsuarioDTO.class);
        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> listarRegistros() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream().map(u -> modelMapper.map(u, UsuarioDTO.class)).toList();
        return usuarioDTOs;
    }

    @Override
    public List<UsuarioDTO> listarRegistrosDisponibles() {
        List<Usuario> usuarios = usuarioRepository.findByDeletedAtNull();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream().map(u -> modelMapper.map(u, UsuarioDTO.class)).toList();
        return usuarioDTOs;
    }

    

}
