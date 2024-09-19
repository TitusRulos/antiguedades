package com.example.antiguedades.entities.estadopersona.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.estadopersona.domain.entity.EstadoPersona;

public interface EstadoPersonaInterface {
    void save(EstadoPersona estadoPersona);

    void delete(EstadoPersona estadoPersona);

    void update(Long id, EstadoPersona estadoPersona);

    List<EstadoPersona> findAll();

    Optional<EstadoPersona> findById(Long id);
}
