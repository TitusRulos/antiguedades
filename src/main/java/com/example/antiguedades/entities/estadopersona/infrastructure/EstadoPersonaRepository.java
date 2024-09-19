package com.example.antiguedades.entities.estadopersona.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.estadopersona.domain.entity.EstadoPersona;

public interface EstadoPersonaRepository extends JpaRepository<EstadoPersona, Long> {
}
