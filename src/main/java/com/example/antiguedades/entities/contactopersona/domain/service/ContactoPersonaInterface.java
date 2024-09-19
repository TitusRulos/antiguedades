package com.example.antiguedades.entities.contactopersona.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.contactopersona.domain.entity.ContactoPersona;

public interface ContactoPersonaInterface {
    void save(ContactoPersona contactoPersona);

    void delete(ContactoPersona contactoPersona);

    void update(Long id, ContactoPersona contactoPersona);

    List<ContactoPersona> findAll();

    Optional<ContactoPersona> findById(Long id);
}
