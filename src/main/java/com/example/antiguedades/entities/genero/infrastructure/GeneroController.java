package com.example.antiguedades.entities.genero.infrastructure;

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

import com.example.antiguedades.entities.genero.application.GeneroServiceImpl;
import com.example.antiguedades.entities.genero.domain.entity.Genero;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    @GetMapping
    public List<Genero> listAll() {
        return generoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Genero> genero = generoService.findById(id);
        if (genero.isPresent()) {
            return ResponseEntity.ok(genero.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genero not found with id: " + id);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Genero genero, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body("Genero created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Genero genero, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            generoService.update(id, genero);
            return ResponseEntity.ok("Genero updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Genero> genero = generoService.findById(id);
        if (genero.isPresent()) {
            generoService.delete(genero.get());
            return ResponseEntity.ok("Genero deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genero not found with id: " + id);
        }
    }
}
