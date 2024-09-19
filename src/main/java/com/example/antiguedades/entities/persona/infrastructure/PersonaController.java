package com.example.antiguedades.entities.persona.infrastructure;

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

import com.example.antiguedades.entities.persona.application.PersonaServiceImpl;
import com.example.antiguedades.entities.persona.domain.entity.Persona;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping
    public List<Persona> listAll() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona not found with id: " + id);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        personaService.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body("Persona created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Persona persona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            personaService.update(id, persona);
            return ResponseEntity.ok("Persona updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            personaService.delete(persona.get());
            return ResponseEntity.ok("Persona deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona not found with id: " + id);
        }
    }
}
