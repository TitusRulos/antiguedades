package com.example.antiguedades.entities.genero.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.antiguedades.entities.genero.domain.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
