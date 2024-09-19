package com.example.antiguedades.entities.antiguedad.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArsConstructor
public class Antiguedad {
    @Id
    @GeneratedValue(strategy = GeneratedType.IDENTIFY)
    
}
