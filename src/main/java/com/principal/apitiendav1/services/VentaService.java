package com.principal.apitiendav1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.venta.VentaDTO;
import com.principal.apitiendav1.dto.venta.VentaRequestDTO;
import com.principal.apitiendav1.entities.Producto;
import com.principal.apitiendav1.entities.Usuario;
import com.principal.apitiendav1.entities.Venta;
import com.principal.apitiendav1.repositories.ProductoRepository;
import com.principal.apitiendav1.repositories.UsuarioRepository;
import com.principal.apitiendav1.repositories.VentaProductoRepository;
import com.principal.apitiendav1.repositories.VentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService implements IServices<VentaDTO, VentaRequestDTO> {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    @Override
    public VentaDTO actualizarRegistro(Long id, VentaRequestDTO datosRegistro) {

        return null;
    }

    @Override
    public void eliminarRegistro(Long id) {
        
    }

    @Override
    public VentaDTO encontrarRegistro(Long id) {
        return null;
    }

    @Override
    public VentaDTO guardarRegistro(VentaRequestDTO nuevoRegistro) {
            
        return null;
    }

    @Override
    public List<VentaDTO> listarRegistros() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventaDTOs = new ArrayList<>();
        //conversion para la presentacion
        for (Venta venta : ventas) {
            UsuarioDTO usuarioDTO = modelMapper.map(venta.getUsuario(), UsuarioDTO.class);
            VentaDTO ventaDTO = modelMapper.map(venta, VentaDTO.class);
            ventaDTO.setUsuarioDTO(usuarioDTO);
            ventaDTOs.add(ventaDTO);
        }
        return ventaDTOs;
    }

    @Override
    public List<VentaDTO> listarRegistrosDisponibles() {
        List<Venta> ventas = ventaRepository.findByDeletedAtNull();
        List<VentaDTO> ventaDTOs = new ArrayList<>();
        //conversion para la presentacion
        for (Venta venta : ventas) {
            UsuarioDTO usuarioDTO = modelMapper.map(venta.getUsuario(), UsuarioDTO.class);
            VentaDTO ventaDTO = modelMapper.map(venta, VentaDTO.class);
            ventaDTO.setUsuarioDTO(usuarioDTO);
            ventaDTOs.add(ventaDTO);
        }
        return ventaDTOs;
    }
}
