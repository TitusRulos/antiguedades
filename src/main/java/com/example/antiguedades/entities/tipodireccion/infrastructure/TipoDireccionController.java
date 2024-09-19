package com.example.antiguedades.entities.tipodireccion.infrastructure;

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

import com.example.antiguedades.entities.tipodireccion.application.TipoDireccionServiceImpl;
import com.example.antiguedades.entities.tipodireccion.domain.entity.TipoDireccion;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipodireccion")
public class TipoDireccionController {

    @Autowired
    private TipoDireccionServiceImpl tipoDireccionService;

    @GetMapping
    public List<TipoDireccion> listAll() {
        return tipoDireccionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<TipoDireccion> tipoDireccion = tipoDireccionService.findById(id);
        if (tipoDireccion.isPresent()) {
            return ResponseEntity.ok(tipoDireccion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TipoDireccion not found with id: " + id);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TipoDireccion tipoDireccion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        tipoDireccionService.save(tipoDireccion);
        return ResponseEntity.status(HttpStatus.CREATED).body("TipoDireccion created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TipoDireccion tipoDireccion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            tipoDireccionService.update(id, tipoDireccion);
            return ResponseEntity.ok("TipoDireccion updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<TipoDireccion> tipoDireccion = tipoDireccionService.findById(id);
        if (tipoDireccion.isPresent()) {
            tipoDireccionService.delete(tipoDireccion.get());
            return ResponseEntity.ok("TipoDireccion deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TipoDireccion not found with id: " + id);
        }
    }
}
