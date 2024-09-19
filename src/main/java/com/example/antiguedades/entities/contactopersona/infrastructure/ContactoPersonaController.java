package com.example.antiguedades.entities.contactopersona.infrastructure;

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

import com.example.antiguedades.entities.contactopersona.application.ContactoPersonaServiceImpl;
import com.example.antiguedades.entities.contactopersona.domain.entity.ContactoPersona;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacto-persona")
public class ContactoPersonaController {

    @Autowired
    private ContactoPersonaServiceImpl contactoPersonaService;

    @GetMapping
    public List<ContactoPersona> listAllContactos() {
        return contactoPersonaService.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createContacto(@Valid @RequestBody ContactoPersona contactoPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        contactoPersonaService.save(contactoPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contacto creado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContacto(@PathVariable Long id, @Valid @RequestBody ContactoPersona updatedContactoPersona, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            contactoPersonaService.update(id, updatedContactoPersona);
            return ResponseEntity.ok("Contacto actualizado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error actualizando el contacto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContacto(@PathVariable Long id) {
        try {
            Optional<ContactoPersona> contactoPersona = contactoPersonaService.findById(id);
            if (contactoPersona.isPresent()) {
                contactoPersonaService.delete(contactoPersona.get());
                return ResponseEntity.ok("Contacto eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrado con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error eliminando el contacto: " + e.getMessage());
        }
    }
}
