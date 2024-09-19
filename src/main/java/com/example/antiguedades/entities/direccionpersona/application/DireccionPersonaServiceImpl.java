package com.example.antiguedades.entities.direccionpersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.direccionpersona.domain.entity.DireccionPersona;
import com.example.antiguedades.entities.direccionpersona.domain.service.DireccionPersonaInterface;
import com.example.antiguedades.entities.direccionpersona.infrastructure.DireccionPersonaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DireccionPersonaServiceImpl implements DireccionPersonaInterface {

    @Autowired
    private DireccionPersonaRepository direccionPersonaRepository;

    @Override
    @Transactional
    public void save(DireccionPersona direccionPersona) {
        direccionPersonaRepository.save(direccionPersona);
    }

    @Override
    @Transactional
    public void delete(DireccionPersona direccionPersona) {
        direccionPersonaRepository.delete(direccionPersona);
    }

    @Override
    @Transactional
    public void update(Long id, DireccionPersona direccionPersona) {
        Optional<DireccionPersona> existingDireccion = direccionPersonaRepository.findById(id);
        if (existingDireccion.isPresent()) {
            DireccionPersona updatedDireccion = existingDireccion.get();
            updatedDireccion.setDireccion(direccionPersona.getDireccion());
            updatedDireccion.setCiudad(direccionPersona.getCiudad());
            updatedDireccion.setPersona(direccionPersona.getPersona());
            updatedDireccion.setTipoDireccion(direccionPersona.getTipoDireccion());
            direccionPersonaRepository.save(updatedDireccion);
        } else {
            throw new EntityNotFoundException("DireccionPersona not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<DireccionPersona> findAll() {
        return direccionPersonaRepository.findAll();
    }

    @Override
    public Optional<DireccionPersona> findById(Long id) {
        return direccionPersonaRepository.findById(id);
    }
}
