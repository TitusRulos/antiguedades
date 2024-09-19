package com.example.antiguedades.entities.direccionpersona.domain.entity;

import com.example.antiguedades.entities.ciudad.domain.entity.Ciudad;
import com.example.antiguedades.entities.persona.domain.entity.Persona;
import com.example.antiguedades.entities.tipodireccion.domain.entity.TipoDireccion;

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
@Table(name="direccion_persona")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DireccionPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "tipodireccion_id")
    private TipoDireccion tipoDireccion;

    @Column(name = "direccion")
    private String direccion;
}
