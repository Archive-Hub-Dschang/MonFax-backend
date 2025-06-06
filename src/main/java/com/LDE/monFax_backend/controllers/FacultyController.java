package com.LDE.monFax_backend.controllers;

import com.LDE.monFax_backend.models.Faculty;
import com.LDE.monFax_backend.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    // 🔹 Créer une nouvelle faculté
    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(savedFaculty);
    }

    // 🔹 Récupérer toutes les facultés
    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    // 🔹 Récupérer une faculté par ID
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Mettre à jour une faculté
    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails) {
        return facultyService.updateFaculty(id, facultyDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Supprimer une faculté (et ses départements)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        boolean deleted = facultyService.deleteFaculty(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}