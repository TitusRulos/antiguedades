package com.example.antiguedades.entities.pais.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.pais.domain.entity.Pais;

public interface PaisInterface {
    void save(Pais pais);
    void delete(Pais pais);
    void update(Long id, Pais pais);
    List<Pais> findAll();
    Optional<Pais> findById(Long id);
}
