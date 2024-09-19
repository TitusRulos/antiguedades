package com.example.antiguedades.entities.ciudad.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.ciudad.domain.entity.Ciudad;

public interface CiudadInterface {
    void save(Ciudad ciudad);
    
    void delete(Ciudad ciudad);
    
    void update(Long id, Ciudad ciudad);
    
    List<Ciudad> findAll();
    
    Optional<Ciudad> findById(Long id);
}
