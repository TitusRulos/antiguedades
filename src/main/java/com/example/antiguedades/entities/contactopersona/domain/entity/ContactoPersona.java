package com.example.antiguedades.entities.contactopersona.domain.entity;

import com.example.antiguedades.entities.persona.domain.entity.Persona;

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
@Table(name="contacto_persona")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;
}
