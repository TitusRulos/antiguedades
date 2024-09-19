package com.example.antiguedades.entities.direccionpersona.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.direccionpersona.domain.entity.DireccionPersona;

public interface DireccionPersonaRepository extends JpaRepository<DireccionPersona, Long> {
}
