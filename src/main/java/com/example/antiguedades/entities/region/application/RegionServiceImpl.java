package com.example.antiguedades.entities.region.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.antiguedades.entities.region.domain.entity.Region;
import com.example.antiguedades.entities.region.domain.service.RegionInterface;
import com.example.antiguedades.entities.region.infrastructure.RegionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegionServiceImpl implements RegionInterface {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    @Transactional
    public void save(Region region) {
        regionRepository.save(region);
    }

    @Override
    @Transactional
    public void delete(Region region) {
        regionRepository.delete(region);
    }

    @Override
    @Transactional
    public void update(Long id, Region region) {
        Optional<Region> existingRegion = regionRepository.findById(id);
        
        if (existingRegion.isPresent()) {
            Region foundRegion = existingRegion.get();
            foundRegion.setNombre(region.getNombre());
            foundRegion.setPais(region.getPais());
            regionRepository.save(foundRegion);
        } else {
            throw new EntityNotFoundException("Region not found with id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Region> findById(Long id) {
        return regionRepository.findById(id);
    }
}
