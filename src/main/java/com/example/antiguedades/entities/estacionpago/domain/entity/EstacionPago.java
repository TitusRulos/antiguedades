package com.example.antiguedades.entities.estacionpago.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estacion_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ubicacion", nullable = true)
    private String ubicacion;
    
    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha_creacion;
}
