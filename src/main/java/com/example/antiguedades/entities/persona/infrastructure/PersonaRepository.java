package com.example.antiguedades.entities.persona.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.persona.domain.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
