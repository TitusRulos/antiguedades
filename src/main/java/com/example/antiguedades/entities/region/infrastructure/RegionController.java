package com.example.antiguedades.entities.region.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.antiguedades.entities.region.application.RegionServiceImpl;
import com.example.antiguedades.entities.region.domain.entity.Region;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionServiceImpl regionService;

    @GetMapping
    public List<Region> listAllRegions() {
        return regionService.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createRegion(@Valid @RequestBody Region region, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        regionService.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body("Region created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable Long id, @Valid @RequestBody Region updatedRegion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            regionService.update(id, updatedRegion);
            return ResponseEntity.ok("Region updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Region: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable Long id) {
        try {
            Optional<Region> region = regionService.findById(id);
            if (region.isPresent()) {
                regionService.delete(region.get());
                return ResponseEntity.ok("Region deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Region: " + e.getMessage());
        }
    }
}
