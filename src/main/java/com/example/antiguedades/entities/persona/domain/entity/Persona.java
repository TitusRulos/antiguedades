package com.example.antiguedades.entities.persona.domain.entity;

import com.example.antiguedades.entities.estadopersona.domain.entity.EstadoPersona;
import com.example.antiguedades.entities.genero.domain.entity.Genero;
import com.example.antiguedades.entities.tipopersona.domain.entity.TipoPersona;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="persona")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(255)")
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoPersona estado;

    @ManyToOne
    @JoinColumn(name = "tipo_persona")
    private TipoPersona tipoPersona;
}
