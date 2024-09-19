package com.example.antiguedades.entities.tipotransaccion.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
}
