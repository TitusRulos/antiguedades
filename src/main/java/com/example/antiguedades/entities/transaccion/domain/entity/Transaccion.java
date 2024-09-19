package com.example.antiguedades.entities.transaccion.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.antiguedades.entities.detalletransaccion.domain.entity.DetalleTransaccion;
import com.example.antiguedades.entities.estacionpago.domain.entity.EstacionPago;
import com.example.antiguedades.entities.persona.domain.entity.Persona;
import com.example.antiguedades.entities.tipotransaccion.domain.TipoTransaccion;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipo_transaccion_id", nullable = false)
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = false)
    private Persona comprador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Persona vendedor;

    @ManyToOne
    @JoinColumn(name = "estacion_pago_id", nullable = false)
    private EstacionPago estacionPago;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL)
    private List<DetalleTransaccion> detalles;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    
    @Column(name = "precio_total", precision = 10, scale = 2, nullable = true)
    private BigDecimal precioTotal;
}
