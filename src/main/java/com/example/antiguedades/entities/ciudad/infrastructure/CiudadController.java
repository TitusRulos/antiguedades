package com.example.antiguedades.entities.ciudad.infrastructure;

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

import com.example.antiguedades.entities.ciudad.application.CiudadServiceImpl;
import com.example.antiguedades.entities.ciudad.domain.entity.Ciudad;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {

    @Autowired
    private CiudadServiceImpl ciudadService;

    @GetMapping
    public List<Ciudad> listAllCiudades() {
        return ciudadService.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createCiudad(@Valid @RequestBody Ciudad ciudad, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        ciudadService.save(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ciudad creada exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCiudad(@PathVariable Long id, @Valid @RequestBody Ciudad updatedCiudad, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<Ciudad> existingCiudad = ciudadService.findById(id);
            if (existingCiudad.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ciudad no encontrada con el ID: " + id);
            }

            ciudadService.update(id, updatedCiudad);
            return ResponseEntity.ok("Ciudad actualizada exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error actualizando la ciudad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCiudad(@PathVariable Long id) {
        try {
            Optional<Ciudad> ciudad = ciudadService.findById(id);
            if (ciudad.isPresent()) {
                ciudadService.delete(ciudad.get());
                return ResponseEntity.ok("Ciudad eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ciudad no encontrada con el ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error eliminando la ciudad: " + e.getMessage());
        }
    }
}
