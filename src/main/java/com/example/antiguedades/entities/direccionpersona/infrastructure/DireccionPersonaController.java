package com.example.antiguedades.entities.direccionpersona.infrastructure;

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

import com.example.antiguedades.entities.direccionpersona.application.DireccionPersonaServiceImpl;
import com.example.antiguedades.entities.direccionpersona.domain.entity.DireccionPersona;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/direccionpersona")
public class DireccionPersonaController {

    @Autowired
    private DireccionPersonaServiceImpl direccionPersonaService;

    @GetMapping
    public List<DireccionPersona> listAll() {
        return direccionPersonaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<DireccionPersona> direccionPersona = direccionPersonaService.findById(id);
        if (direccionPersona.isPresent()) {
            return ResponseEntity.ok(direccionPersona.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DireccionPersona not found with id: " + id);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DireccionPersona direccionPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        direccionPersonaService.save(direccionPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body("DireccionPersona created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DireccionPersona direccionPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            direccionPersonaService.update(id, direccionPersona);
            return ResponseEntity.ok("DireccionPersona updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<DireccionPersona> direccionPersona = direccionPersonaService.findById(id);
        if (direccionPersona.isPresent()) {
            direccionPersonaService.delete(direccionPersona.get());
            return ResponseEntity.ok("DireccionPersona deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DireccionPersona not found with id: " + id);
        }
    }
}
