package com.example.antiguedades.entities.contactopersona.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.contactopersona.domain.entity.ContactoPersona;

public interface ContactoPersonaRepository extends JpaRepository<ContactoPersona, Long> {
}
