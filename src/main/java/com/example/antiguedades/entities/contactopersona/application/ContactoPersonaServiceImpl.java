package com.example.antiguedades.entities.contactopersona.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.antiguedades.entities.contactopersona.domain.entity.ContactoPersona;
import com.example.antiguedades.entities.contactopersona.domain.service.ContactoPersonaInterface;
import com.example.antiguedades.entities.contactopersona.infrastructure.ContactoPersonaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactoPersonaServiceImpl implements ContactoPersonaInterface {

    @Autowired
    private ContactoPersonaRepository contactoPersonaRepository;

    @Override
    @Transactional
    public void save(ContactoPersona contactoPersona) {
        contactoPersonaRepository.save(contactoPersona);
    }

    @Override
    @Transactional
    public void delete(ContactoPersona contactoPersona) {
        contactoPersonaRepository.delete(contactoPersona);
    }

    @Override
    @Transactional
    public void update(Long id, ContactoPersona contactoPersona) {
        Optional<ContactoPersona> existingContactoPersona = contactoPersonaRepository.findById(id);

        if (existingContactoPersona.isPresent()) {
            ContactoPersona foundContactoPersona = existingContactoPersona.get();
            foundContactoPersona.setTelefono(contactoPersona.getTelefono());
            foundContactoPersona.setEmail(contactoPersona.getEmail());
            foundContactoPersona.setPersona(contactoPersona.getPersona());
            contactoPersonaRepository.save(foundContactoPersona);
        } else {
            throw new EntityNotFoundException("ContactoPersona not found with id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactoPersona> findAll() {
        return contactoPersonaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactoPersona> findById(Long id) {
        return contactoPersonaRepository.findById(id);
    }
}
