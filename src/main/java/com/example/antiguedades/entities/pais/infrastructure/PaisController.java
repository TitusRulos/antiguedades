package com.example.antiguedades.entities.pais.infrastructure;

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

import com.example.antiguedades.entities.pais.application.PaisServiceImpl;
import com.example.antiguedades.entities.pais.domain.entity.Pais;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisServiceImpl paisService;

    @GetMapping
    public List<Pais> listAllPaises() {
        return paisService.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createPais(@Valid @RequestBody Pais pais, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        paisService.save(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body("País creado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePais(@PathVariable Long id, @Valid @RequestBody Pais updatedPais, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<Pais> existingPais = paisService.findById(id);
            if (existingPais.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("País no encontrado con el ID: " + id);
            }

            paisService.update(id, updatedPais);
            return ResponseEntity.ok("País actualizado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error actualizando el país: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePais(@PathVariable Long id) {
        try {
            Optional<Pais> pais = paisService.findById(id);
            if (pais.isPresent()) {
                paisService.delete(pais.get());
                return ResponseEntity.ok("País eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("País no encontrado con el ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error eliminando el país: " + e.getMessage());
        }
    }
}
