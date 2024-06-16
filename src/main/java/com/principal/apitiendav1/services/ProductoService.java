package com.principal.apitiendav1.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.producto.ProductoRequestDTO;
import com.principal.apitiendav1.entities.Categoria;
import com.principal.apitiendav1.entities.Producto;
import com.principal.apitiendav1.repositories.CategoriaRepository;
import com.principal.apitiendav1.repositories.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService implements IServices<ProductoDTO, ProductoRequestDTO> {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProductoDTO actualizarRegistro(Long id, ProductoRequestDTO datosRegistro) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        producto.setDescripcion(datosRegistro.getDescripcion());
        producto.setNombre(datosRegistro.getNombre());
        producto.setStock(datosRegistro.getStock());
        //verificar la categoria
        Categoria categoriaEncontrada = categoriaRepository.findById(datosRegistro.getCategoria().getId()).orElseThrow(() -> new EntityNotFoundException());
        producto.setCategoria(categoriaEncontrada);
        productoRepository.save(producto);
        return modelMapper.map(producto, ProductoDTO.class);
    }

    @Override
    public void eliminarRegistro(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        producto.setDeletedAt(LocalDateTime.now());
        productoRepository.save(producto);
    }

    @Override
    public ProductoDTO encontrarRegistro(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(producto, ProductoDTO.class);
    }

    @Override
    public ProductoDTO guardarRegistro(ProductoRequestDTO nuevoRegistro) {
        Producto nuevoProducto = modelMapper.map(nuevoRegistro, Producto.class);
        ProductoDTO productoDTO = modelMapper.map(productoRepository.save(nuevoProducto), ProductoDTO.class);
        return productoDTO;
    }

    @Override
    public List<ProductoDTO> listarRegistros() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = productos.stream().map(p -> modelMapper.map(p, ProductoDTO.class)).toList();
        return productosDTO;
    }

    @Override
    public List<ProductoDTO> listarRegistrosDisponibles() {
        List<Producto> productos = productoRepository.findByDeletedAtNull();
        List<ProductoDTO> productoDTOs = productos.stream().map(p -> modelMapper.map(p, ProductoDTO.class)).collect(Collectors.toList());
        return productoDTOs;
    }
}
