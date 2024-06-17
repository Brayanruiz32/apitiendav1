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
        Venta nuevaVenta = modelMapper.map(nuevoRegistro, Venta.class);
        if (nuevoRegistro.getUsuarioDTO() == null && nuevoRegistro.getProductosDTOs() == null) {
            throw new IllegalArgumentException("Hay un null que esta yendo");
        }
        //guardo los datos del usuario
        Usuario usuario = usuarioRepository.findById(nuevoRegistro.getUsuarioDTO().getId()).orElseThrow(() -> new EntityNotFoundException());
        nuevaVenta.setUsuario(usuario);
        //guardo los datos del producto
        List<Producto> productos = nuevoRegistro.getProductosDTOs()
        .stream()
        .map(p -> productoRepository.findById(p.getId())
        .orElseThrow(() -> new EntityNotFoundException()))
        .collect(Collectors.toList());        
        nuevaVenta.setProductos(productos);
        //guardo en la bd la nueva venta
        ventaRepository.save(nuevaVenta);
        //conversion para la presentacion
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        VentaDTO ventaDTO = modelMapper.map(ventaRepository.save(nuevaVenta), VentaDTO.class);
        List<ProductoDTO> productoDTOs = productos
        .stream()
        .map(p -> modelMapper.map(p, ProductoDTO.class)).collect(Collectors.toList());
        ventaDTO.setUsuarioDTO(usuarioDTO);
        ventaDTO.setProductos(productoDTOs);
        return ventaDTO;
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
