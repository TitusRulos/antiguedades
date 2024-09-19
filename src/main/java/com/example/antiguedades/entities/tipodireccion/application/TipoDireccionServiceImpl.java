package com.example.antiguedades.entities.tipodireccion.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.antiguedades.entities.tipodireccion.domain.entity.TipoDireccion;
import com.example.antiguedades.entities.tipodireccion.domain.service.TipoDireccionInterface;
import com.example.antiguedades.entities.tipodireccion.infrastructure.TipoDireccionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TipoDireccionServiceImpl implements TipoDireccionInterface {

    @Autowired
    private TipoDireccionRepository tipoDireccionRepository;

    @Override
    @Transactional
    public void save(TipoDireccion tipoDireccion) {
        tipoDireccionRepository.save(tipoDireccion);
    }

    @Override
    @Transactional
    public void delete(TipoDireccion tipoDireccion) {
        tipoDireccionRepository.delete(tipoDireccion);
    }

    @Override
    @Transactional
    public void update(Long id, TipoDireccion tipoDireccion) {
        Optional<TipoDireccion> existingTipoDireccion = tipoDireccionRepository.findById(id);
        if (existingTipoDireccion.isPresent()) {
            TipoDireccion updatedTipoDireccion = existingTipoDireccion.get();
            updatedTipoDireccion.setDescripcion(tipoDireccion.getDescripcion());
            tipoDireccionRepository.save(updatedTipoDireccion);
        } else {
            throw new EntityNotFoundException("TipoDireccion not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<TipoDireccion> findAll() {
        return tipoDireccionRepository.findAll();
    }

    @Override
    public Optional<TipoDireccion> findById(Long id) {
        return tipoDireccionRepository.findById(id);
    }
}
