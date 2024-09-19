package com.example.antiguedades.entities.genero.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.genero.domain.entity.Genero;

public interface GeneroInterface {
    void save(Genero genero);

    void delete(Genero genero);

    void update(Long id, Genero genero);

    List<Genero> findAll();

    Optional<Genero> findById(Long id);
}
