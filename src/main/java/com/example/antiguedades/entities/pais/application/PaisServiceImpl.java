package com.example.antiguedades.entities.pais.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.antiguedades.entities.pais.domain.entity.Pais;
import com.example.antiguedades.entities.pais.domain.service.PaisInterface;
import com.example.antiguedades.entities.pais.infrastructure.PaisRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaisServiceImpl implements PaisInterface {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    @Transactional
    public void save(Pais pais) {
        paisRepository.save(pais);
    }

    @Override
    @Transactional
    public void delete(Pais pais) {
        paisRepository.delete(pais);
    }

    @Override
    @Transactional
    public void update(Long id, Pais pais) {
        Optional<Pais> existingPais = paisRepository.findById(id);

        if (existingPais.isPresent()) {
            Pais foundPais = existingPais.get();
            foundPais.setNombre(pais.getNombre());
            paisRepository.save(foundPais);
        } else {
            throw new EntityNotFoundException("País no encontrado con el ID: " + id);
        }
    }

    @Override
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return paisRepository.findById(id);
    }
}
