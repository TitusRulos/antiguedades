package com.example.antiguedades.entities.genero.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.genero.domain.entity.Genero;
import com.example.antiguedades.entities.genero.domain.service.GeneroInterface;
import com.example.antiguedades.entities.genero.infrastructure.GeneroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class GeneroServiceImpl implements GeneroInterface {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public void save(Genero genero) {
        generoRepository.save(genero);
    }

    @Override
    @Transactional
    public void delete(Genero genero) {
        generoRepository.delete(genero);
    }

    @Override
    @Transactional
    public void update(Long id, Genero genero) {
        Optional<Genero> existingGenero = generoRepository.findById(id);
        if (existingGenero.isPresent()) {
            Genero updatedGenero = existingGenero.get();
            updatedGenero.setNombre(genero.getNombre());
            generoRepository.save(updatedGenero);
        } else {
            throw new EntityNotFoundException("Genero not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    @Override
    public Optional<Genero> findById(Long id) {
        return generoRepository.findById(id);
    }
}
