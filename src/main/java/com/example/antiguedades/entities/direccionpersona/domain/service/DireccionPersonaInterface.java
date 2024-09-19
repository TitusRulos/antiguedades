package com.example.antiguedades.entities.direccionpersona.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.direccionpersona.domain.entity.DireccionPersona;

public interface DireccionPersonaInterface {
    void save(DireccionPersona direccionPersona);

    void delete(DireccionPersona direccionPersona);

    void update(Long id, DireccionPersona direccionPersona);

    List<DireccionPersona> findAll();

    Optional<DireccionPersona> findById(Long id);
}
