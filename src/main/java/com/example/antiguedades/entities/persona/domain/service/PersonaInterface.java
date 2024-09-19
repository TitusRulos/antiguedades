package com.example.antiguedades.entities.persona.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.persona.domain.entity.Persona;

public interface PersonaInterface {
    void save(Persona persona);

    void delete(Persona persona);

    void update(Long id, Persona persona);

    List<Persona> findAll();

    Optional<Persona> findById(Long id);
}
