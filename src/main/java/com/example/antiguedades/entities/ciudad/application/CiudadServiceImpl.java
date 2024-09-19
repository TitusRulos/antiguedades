package com.example.antiguedades.entities.ciudad.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.ciudad.domain.entity.Ciudad;
import com.example.antiguedades.entities.ciudad.domain.service.CiudadInterface;
import com.example.antiguedades.entities.ciudad.infrastructure.CiudadRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CiudadServiceImpl implements CiudadInterface {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    @Transactional
    public void save(Ciudad ciudad) {
        ciudadRepository.save(ciudad);
    }

    @Override
    @Transactional
    public void delete(Ciudad ciudad) {
        ciudadRepository.delete(ciudad);
    }

    @Override
    @Transactional
    public void update(Long id, Ciudad ciudad) {
        Optional<Ciudad> existingCiudad = ciudadRepository.findById(id);
        if (existingCiudad.isPresent()) {
            Ciudad foundCiudad = existingCiudad.get();
            foundCiudad.setNombre(ciudad.getNombre());
            foundCiudad.setRegion(ciudad.getRegion());
            ciudadRepository.save(foundCiudad);
        } else {
            throw new EntityNotFoundException("Ciudad no encontrada con el ID: " + id);
        }
    }

    @Override
    @Transactional
    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    @Override
    public Optional<Ciudad> findById(Long id) {
        return ciudadRepository.findById(id);
    }
}
