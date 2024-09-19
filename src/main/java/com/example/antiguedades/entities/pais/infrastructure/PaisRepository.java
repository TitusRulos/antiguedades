package com.example.antiguedades.entities.pais.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.pais.domain.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{

}
