package com.principal.apitiendav1.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.producto.VentaProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import com.principal.apitiendav1.dto.venta.VentaDTO;
import com.principal.apitiendav1.dto.venta.VentaRequestDTO;
import com.principal.apitiendav1.entities.Usuario;
import com.principal.apitiendav1.entities.Venta;
import com.principal.apitiendav1.entities.VentaProducto;
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
    private VentaProductoRepository ventaProductoRepository;

    @Autowired
    private VentaProductoService ventaProductoService;

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
        List<VentaProducto> ventasProductos = ventaProductoService.crearVentasProductos(datosRegistro.getProductosVentasDtos(), venta);

        for (VentaProducto ventaProducto : ventasProductos) {
            montoTotal = montoTotal.add(ventaProducto.getSubtotal());
        }

        venta.setMontoTotal(montoTotal);
        ventaRepository.save(venta);
        ventaProductoRepository.saveAll(ventasProductos);
        //setear los valores para devolver
        VentaDTO ventaDTO = convertirAVentaDTO(ventasProductos, usuario, venta);
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
        List<VentaProducto> ventasProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
        return convertirAVentaDTO(ventasProductos, usuario, venta);
    }

    @Override
    @Transactional
    public VentaDTO guardarRegistro(VentaRequestDTO nuevoRegistro) {
        //creamos la venta 
        Venta nuevaVenta = new Venta();
        //seteamos los valores para realizar la venta
        Usuario usuario = usuarioRepository.findById(nuevoRegistro.getUsuarioDTO().getId()).orElseThrow(() -> new EntityNotFoundException());
        nuevaVenta.setUsuario(usuario);
        nuevaVenta.setEstadoVenta(nuevoRegistro.getEstadoVenta());
        //declaramos las ventas de los productos
        BigDecimal montoTotal = new BigDecimal(0);
        List<VentaProducto> ventasProductos = ventaProductoService.crearVentasProductos(nuevoRegistro.getProductosVentasDtos(), nuevaVenta);
        for (VentaProducto ventaProducto : ventasProductos) {
            montoTotal = montoTotal.add(ventaProducto.getSubtotal());
        }
        //guardo la venta y la ventaproducto
        nuevaVenta.setMontoTotal(montoTotal);
        ventaRepository.save(nuevaVenta);
        ventaProductoRepository.saveAll(ventasProductos);
        //preparo los datos para la respuesta
        return convertirAVentaDTO(ventasProductos, usuario, nuevaVenta);
    }

    @Override
    public List<VentaDTO> listarRegistros() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventaDTOs = this.crearListaDTO(ventas);
        return ventaDTOs;
    }

    @Override
    public List<VentaDTO> listarRegistrosDisponibles() {
        List<Venta> ventas = ventaRepository.findByDeletedAtNull();
        List<VentaDTO> ventaDTOs = this.crearListaDTO(ventas);
        return ventaDTOs;
    }


    //codigo adicional para simplificacion
    private VentaDTO convertirAVentaDTO(List<VentaProducto> lista, Usuario usuario, Venta venta){
        List<VentaProductoDTO> ventaProductoDTOs = lista.stream().map(vp -> modelMapper.map(vp, VentaProductoDTO.class)).toList();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        VentaDTO ventaDTO = modelMapper.map(venta, VentaDTO.class);
        ventaDTO.setProductosVentasDtos(ventaProductoDTOs);
        ventaDTO.setUsuarioDTO(usuarioDTO);
        return ventaDTO;
    }


    private List<VentaDTO> crearListaDTO(List<Venta> ventas){
        List<VentaDTO> ventaDTOs = new ArrayList<>();
        for (Venta venta : ventas) {
            List<VentaProducto> ventasProductos = ventaProductoRepository.findByIdVentaId(venta.getId());
            VentaDTO ventaDTO = convertirAVentaDTO(ventasProductos, venta.getUsuario(), venta);
            ventaDTOs.add(ventaDTO);
        }
        return ventaDTOs;
    }
}
