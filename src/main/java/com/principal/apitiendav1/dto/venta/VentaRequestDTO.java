package com.principal.apitiendav1.dto.venta;

import java.math.BigDecimal;
import java.util.List;

import com.principal.apitiendav1.dto.producto.ProductoDTO;
import com.principal.apitiendav1.dto.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequestDTO {

    private BigDecimal montoTotal;
    private UsuarioDTO usuarioDTO;
    private List<ProductoDTO> productosDTOs;
    
}
