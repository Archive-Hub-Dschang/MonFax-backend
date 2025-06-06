package com.LDE.monFax_backend.services;

import com.LDE.monFax_backend.models.Faculty;
import com.LDE.monFax_backend.repositories.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FacultyService {

        private final FacultyRepository facultyRepository;

        // 🔹 Créer une faculté
        public Faculty createFaculty(Faculty faculty) {
            return facultyRepository.save(faculty);
        }

        // 🔹 Lire toutes les facultés
        public List<Faculty> getAllFaculties() {
            return facultyRepository.findAll();
        }

        // 🔹 Lire une faculté par ID
        public Optional<Faculty> getFacultyById(Long id) {
            return facultyRepository.findById(id);
        }

        // 🔹 Mettre à jour une faculté
        public Optional<Faculty> updateFaculty(Long id, Faculty facultyDetails) {
            return facultyRepository.findById(id).map(faculty -> {
                faculty.setName(facultyDetails.getName());
                faculty.setDescription(facultyDetails.getDescription());
                return facultyRepository.save(faculty);
            });
        }

        // 🔹 Supprimer une faculté (avec ses départements grâce à cascade = ALL)
        public boolean deleteFaculty(Long id) {
            return facultyRepository.findById(id).map(faculty -> {
                facultyRepository.delete(faculty);
                return true;
            }).orElse(false);
        }
    }

