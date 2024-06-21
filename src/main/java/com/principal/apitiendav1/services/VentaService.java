package com.principal.apitiendav1.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.producto.VentaProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.venta.VentaDTO;
import com.principal.apitiendav1.dto.venta.VentaRequestDTO;
import com.principal.apitiendav1.entities.Producto;
import com.principal.apitiendav1.entities.Usuario;
import com.principal.apitiendav1.entities.Venta;
import com.principal.apitiendav1.entities.VentaProducto;
import com.principal.apitiendav1.entities.VentaProductoId;
import com.principal.apitiendav1.repositories.ProductoRepository;
import com.principal.apitiendav1.repositories.UsuarioRepository;
import com.principal.apitiendav1.repositories.VentaProductoRepository;
import com.principal.apitiendav1.repositories.VentaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
    @Transactional
    public VentaDTO actualizarRegistro(Long id, VentaRequestDTO datosRegistro) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Usuario usuario = usuarioRepository.findById(datosRegistro.getUsuarioDTO().getId()).orElseThrow(() -> new EntityNotFoundException());
        venta.setUsuario(usuario);
        venta.setEstadoVenta(datosRegistro.getEstadoVenta());
        
        List<VentaProducto> ventaProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
        //eliminamos los registros de la tabla intermedia para introducir los nuevos registros
        ventaProductoRepository.deleteAll(ventaProductos);

        //creaciones que serviran
        BigDecimal montoTotal = new BigDecimal(0.00);
        //se crea una nueva lista de nuevo venta productos 
        List<VentaProducto> ventasProductos = new ArrayList<>();


        for (VentaProductoDTO ventaProducto : datosRegistro.getProductosVentasDtos()) {
            Producto producto = productoRepository.findById(ventaProducto.getProducto().getId()).orElseThrow(() -> new EntityNotFoundException());
            int cantidad = ventaProducto.getCantidad();
            BigDecimal precio = producto.getPrecio();
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
            
            //setemos el nuevoventaproducto
            VentaProducto nuevoVentaProducto = new VentaProducto();
            nuevoVentaProducto.setCantidad(cantidad);
            nuevoVentaProducto.setPrecio(precio);
            nuevoVentaProducto.setSubtotal(subtotal);
            nuevoVentaProducto.setVenta(venta);
            nuevoVentaProducto.setProducto(producto);
            ventasProductos.add(nuevoVentaProducto);

            montoTotal = montoTotal.add(subtotal);
        }
        venta.setMontoTotal(montoTotal);
        ventaRepository.save(venta);
        ventaProductoRepository.saveAll(ventasProductos);
        //setear los valores para devolver
        List<VentaProductoDTO> ventaProductoDTOs = ventasProductos.stream()
        .map(vp -> modelMapper.map(vp, VentaProductoDTO.class)).toList();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        VentaDTO ventaDTO = modelMapper.map(venta, VentaDTO.class);
        ventaDTO.setUsuarioDTO(usuarioDTO);
        ventaDTO.setProductosVentasDtos(ventaProductoDTOs);
        return ventaDTO;

    }

    @Override
    public void eliminarRegistro(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        venta.setDeletedAt(LocalDateTime.now());
        ventaRepository.save(venta);
    }

    @Override
    public VentaDTO encontrarRegistro(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Usuario usuario = usuarioRepository.findById(venta.getUsuario().getId()).orElseThrow();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        VentaDTO ventaDTO = modelMapper.map(venta, VentaDTO.class);
        List<VentaProducto> ventasProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
        List<VentaProductoDTO> ventaProductoDTOs = ventasProductos.stream().map(vp -> modelMapper.map(vp, VentaProductoDTO.class))
        .toList();
        ventaDTO.setUsuarioDTO(usuarioDTO);
        ventaDTO.setProductosVentasDtos(ventaProductoDTOs);
        return ventaDTO;
    }

    @Override
    public VentaDTO guardarRegistro(VentaRequestDTO nuevoRegistro) {
        //creamos la venta 
        Venta nuevaVenta = new Venta();
        //seteamos los valores para realizar la venta
        Usuario usuario = usuarioRepository.findById(nuevoRegistro.getUsuarioDTO().getId()).orElseThrow(() -> new EntityNotFoundException());
        nuevaVenta.setUsuario(usuario);
        nuevaVenta.setEstadoVenta(nuevoRegistro.getEstadoVenta());
        //declaramos las ventas de los productos
        List<VentaProducto> ventaProductos = new ArrayList<>();        
        BigDecimal montoTotal = new BigDecimal(0);

        //codigo para setear los ventaproductos
        for (VentaProductoDTO ventaProducto : nuevoRegistro.getProductosVentasDtos()) {
            //busco el producto dentro del repositorio de productos
            Producto producto = productoRepository.findById(ventaProducto.getProducto().getId()).orElseThrow(() -> new EntityNotFoundException());

            int cantidad = ventaProducto.getCantidad();
            BigDecimal precio = producto.getPrecio();
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));

            VentaProducto nuevaVentaProducto = new VentaProducto();
            nuevaVentaProducto.setCantidad(cantidad);
            nuevaVentaProducto.setPrecio(precio);
            nuevaVentaProducto.setSubtotal(subtotal);
            nuevaVentaProducto.setVenta(nuevaVenta);
            nuevaVentaProducto.setProducto(producto);

            ventaProductos.add(nuevaVentaProducto);
            montoTotal = montoTotal.add(subtotal);

        }
        //guardo la venta y la ventaproducto
        nuevaVenta.setMontoTotal(montoTotal);
        ventaRepository.save(nuevaVenta);
        ventaProductoRepository.saveAll(ventaProductos);

        //preparo los datos para la respuesta
        List<VentaProductoDTO> ventasProductosDTOs = ventaProductos.stream()
        .map(vp -> modelMapper.map(vp, VentaProductoDTO.class)).toList();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        VentaDTO ventaDTO = modelMapper.map(nuevaVenta, VentaDTO.class);
        ventaDTO.setProductosVentasDtos(ventasProductosDTOs);
        ventaDTO.setUsuarioDTO(usuarioDTO);
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
            List<VentaProducto> ventasProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
            List<VentaProductoDTO> ventaProductoDTOs = ventasProductos.stream().map(vp -> modelMapper.map(vp, VentaProductoDTO.class))
            .toList();
            ventaDTO.setUsuarioDTO(usuarioDTO);
            ventaDTO.setProductosVentasDtos(ventaProductoDTOs);
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
            List<VentaProducto> ventasProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
            List<VentaProductoDTO> ventaProductoDTOs = ventasProductos.stream().map(vp -> modelMapper.map(vp, VentaProductoDTO.class))
            .toList();
            ventaDTO.setUsuarioDTO(usuarioDTO);
            ventaDTO.setProductosVentasDtos(ventaProductoDTOs);
            ventaDTOs.add(ventaDTO);
        }
        return ventaDTOs;
    }
}
