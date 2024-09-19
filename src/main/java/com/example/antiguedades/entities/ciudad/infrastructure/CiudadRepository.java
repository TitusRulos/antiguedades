package com.example.antiguedades.entities.ciudad.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.ciudad.domain.entity.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
