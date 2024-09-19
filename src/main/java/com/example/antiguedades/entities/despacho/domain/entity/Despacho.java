package com.example.antiguedades.entities.despacho.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.example.antiguedades.entities.transaccion.domain.entity.Transaccion;

@Entity
@Table(name = "despacho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaccion_id", nullable = false)
    private Transaccion transaccion;

    @Column(name = "direccion_entrega", nullable = false)
    private String direccionEntrega;

    @Column(name = "fecha_despacho", nullable = false)
    private LocalDateTime fechaDespacho;

    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha_creacion;
}
