package com.example.antiguedades.entities.estadopersona.infrastructure;

import java.util.List;
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

import com.example.antiguedades.entities.estadopersona.application.EstadoPersonaServiceImpl;
import com.example.antiguedades.entities.estadopersona.domain.entity.EstadoPersona;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estado-persona")
public class EstadoPersonaController {

    @Autowired
    private EstadoPersonaServiceImpl estadoPersonaService;

    @GetMapping
    public List<EstadoPersona> listAll() {
        return estadoPersonaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<EstadoPersona> estadoPersona = estadoPersonaService.findById(id);
        if (estadoPersona.isPresent()) {
            return ResponseEntity.ok(estadoPersona.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EstadoPersona not found with id: " + id);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EstadoPersona estadoPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        estadoPersonaService.save(estadoPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body("EstadoPersona created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EstadoPersona estadoPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            estadoPersonaService.update(id, estadoPersona);
            return ResponseEntity.ok("EstadoPersona updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<EstadoPersona> estadoPersona = estadoPersonaService.findById(id);
        if (estadoPersona.isPresent()) {
            estadoPersonaService.delete(estadoPersona.get());
            return ResponseEntity.ok("EstadoPersona deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EstadoPersona not found with id: " + id);
        }
    }
}
