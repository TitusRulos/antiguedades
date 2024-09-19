package com.example.antiguedades.entities.tipodireccion.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.tipodireccion.domain.entity.TipoDireccion;

public interface TipoDireccionRepository extends JpaRepository<TipoDireccion, Long> {
}
