package com.principal.apitiendav1.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.rol.RolDTO;
import com.principal.apitiendav1.dto.rol.RolRequestDTO;
import com.principal.apitiendav1.entities.Rol;
import com.principal.apitiendav1.repositories.RolRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class RolService implements IServices<RolDTO, RolRequestDTO> {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolDTO actualizarRegistro(Long id, RolRequestDTO datosRegistro) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        rol.setNombre(datosRegistro.getNombre());
        rol.setPermisos(datosRegistro.getPermisos());
        RolDTO rolDTO = modelMapper.map(rolRepository.save(rol), RolDTO.class);
        return rolDTO;
    }

    @Override
    public void eliminarRegistro(Long id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        rol.setDeletedAt(LocalDateTime.now());
        rolRepository.save(rol);
    }

    @Override
    public RolDTO encontrarRegistro(Long id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        RolDTO rolDTO = modelMapper.map(rol, RolDTO.class);
        return rolDTO;
    }

    @Override
    public RolDTO guardarRegistro(RolRequestDTO nuevoRegistro) {
        Rol nuevoRol = modelMapper.map(nuevoRegistro, Rol.class);
        RolDTO rolDTO = modelMapper.map(rolRepository.save(nuevoRol), RolDTO.class);
        return rolDTO;
    }

    @Override
    public List<RolDTO> listarRegistros() {
        List<Rol> roles = rolRepository.findAll();
        List<RolDTO> rolesDTO = roles.stream().map(r -> modelMapper.map(r, RolDTO.class)).toList();
        return rolesDTO;
    }

    @Override
    public List<RolDTO> listarRegistrosDisponibles() {
        List<Rol> roles = rolRepository.findByDeletedAtNull();
        List<RolDTO> rolDTOs = roles.stream().map(r -> modelMapper.map(r, RolDTO.class)).toList();
        return rolDTOs;
    }

    


}
