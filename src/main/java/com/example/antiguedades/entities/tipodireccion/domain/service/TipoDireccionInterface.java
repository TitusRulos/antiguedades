package com.example.antiguedades.entities.tipodireccion.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.tipodireccion.domain.entity.TipoDireccion;

public interface TipoDireccionInterface {
    void save(TipoDireccion tipoDireccion);

    void delete(TipoDireccion tipoDireccion);

    void update(Long id, TipoDireccion tipoDireccion);

    List<TipoDireccion> findAll();

    Optional<TipoDireccion> findById(Long id);
}
