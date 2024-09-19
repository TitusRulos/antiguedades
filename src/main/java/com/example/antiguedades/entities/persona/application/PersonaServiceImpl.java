package com.example.antiguedades.entities.persona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.persona.domain.entity.Persona;
import com.example.antiguedades.entities.persona.domain.service.PersonaInterface;
import com.example.antiguedades.entities.persona.infrastructure.PersonaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PersonaServiceImpl implements PersonaInterface {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional
    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void delete(Persona persona) {
        personaRepository.delete(persona);
    }

    @Override
    @Transactional
    public void update(Long id, Persona persona) {
        Optional<Persona> existingPersona = personaRepository.findById(id);
        if (existingPersona.isPresent()) {
            Persona updatedPersona = existingPersona.get();
            updatedPersona.setNombre(persona.getNombre());
            updatedPersona.setContraseña(persona.getContraseña());
            updatedPersona.setGenero(persona.getGenero());
            updatedPersona.setEstado(persona.getEstado());
            updatedPersona.setTipoPersona(persona.getTipoPersona());
            personaRepository.save(updatedPersona);
        } else {
            throw new EntityNotFoundException("Persona not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }
}
