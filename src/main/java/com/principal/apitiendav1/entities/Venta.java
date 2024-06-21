package com.principal.apitiendav1.entities;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ventas")
public class Venta extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    private EstadoVenta estadoVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Venta(EstadoVenta estadoVenta, Usuario usuario) {
        this.estadoVenta = estadoVenta;
        this.usuario = usuario;
    }

    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER)
    private Set<VentaProducto> productos;

    @Override
    public String toString() {
        return "Venta [id=" + id + ", montoTotal=" + montoTotal + ", estadoVenta=" + estadoVenta + ", usuario="
                + usuario + ", productos=" + productos.toString() + "]";
    }


    
    
    
}
