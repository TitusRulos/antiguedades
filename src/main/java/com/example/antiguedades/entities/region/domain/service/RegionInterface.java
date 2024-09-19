package com.example.antiguedades.entities.region.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.antiguedades.entities.region.domain.entity.Region;

public interface  RegionInterface {
    void save(Region region);
    
    void delete(Region region);
    
    void update(Long id, Region region);
    
    List<Region> findAll();
    
    Optional<Region> findById(Long id);
}
