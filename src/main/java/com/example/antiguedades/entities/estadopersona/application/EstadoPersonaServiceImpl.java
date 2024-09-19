package com.example.antiguedades.entities.estadopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.estadopersona.domain.entity.EstadoPersona;
import com.example.antiguedades.entities.estadopersona.domain.service.EstadoPersonaInterface;
import com.example.antiguedades.entities.estadopersona.infrastructure.EstadoPersonaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EstadoPersonaServiceImpl implements EstadoPersonaInterface {

    @Autowired
    private EstadoPersonaRepository estadoPersonaRepository;

    @Override
    @Transactional
    public void save(EstadoPersona estadoPersona) {
        estadoPersonaRepository.save(estadoPersona);
    }

    @Override
    @Transactional
    public void delete(EstadoPersona estadoPersona) {
        estadoPersonaRepository.delete(estadoPersona);
    }

    @Override
    @Transactional
    public void update(Long id, EstadoPersona estadoPersona) {
        Optional<EstadoPersona> existingEstadoPersona = estadoPersonaRepository.findById(id);
        if (existingEstadoPersona.isPresent()) {
            EstadoPersona updatedEstadoPersona = existingEstadoPersona.get();
            updatedEstadoPersona.setDescripcion(estadoPersona.getDescripcion());
            estadoPersonaRepository.save(updatedEstadoPersona);
        } else {
            throw new EntityNotFoundException("EstadoPersona not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<EstadoPersona> findAll() {
        return estadoPersonaRepository.findAll();
    }

    @Override
    public Optional<EstadoPersona> findById(Long id) {
        return estadoPersonaRepository.findById(id);
    }
}
