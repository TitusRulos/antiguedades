package com.example.antiguedades.entities.region.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antiguedades.entities.region.domain.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
