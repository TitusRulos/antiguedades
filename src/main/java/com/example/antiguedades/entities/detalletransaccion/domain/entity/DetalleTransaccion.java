package com.example.antiguedades.entities.detalletransaccion.domain.entity;

import java.math.BigDecimal;

import com.example.antiguedades.entities.antiguedad.domain.entity.Antiguedad;
import com.example.antiguedades.entities.transaccion.domain.entity.Transaccion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaccion_id", nullable = false)
    private Transaccion transaccion;

    @ManyToOne
    @JoinColumn(name = "antiguedad_id", nullable = false)
    private Antiguedad antiguedad;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

}
