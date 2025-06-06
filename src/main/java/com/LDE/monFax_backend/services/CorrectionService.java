package com.LDE.monFax_backend.services;


import com.LDE.monFax_backend.models.Correction;
import com.LDE.monFax_backend.models.Exam;
import com.LDE.monFax_backend.models.Resource;
import com.LDE.monFax_backend.repositories.CorrectionRepository;
import com.LDE.monFax_backend.repositories.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final ExamRepository examRepository;
    private final ResourceService resourceService;

    public List<Correction> getAllCorrections() {
        return correctionRepository.findAll();
    }

    public Optional<Correction> getCorrectionById(Long id) {
        Optional<Correction> correction =  correctionRepository.findById(id);
        correction.ifPresent(foundCorrection-> {
            resourceService.increaseNumberOfViews(foundCorrection);
            correctionRepository.save(foundCorrection);
        });
        return correction;
    }

    public Correction createCorrection(String title, Double price, Long examId, MultipartFile file) throws IOException {
        // Upload fichier

        String fileUrl = resourceService.storeFile(file, "corrections");

        // Lier à l'examen
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("epreuve introuvable avec l'id : " + examId));

        // Création de l'objet Correction
        Correction correction = new Correction();
        correction.setTitle(title);
        correction.setPrice(price);
        correction.setExam(exam);
        correction.setResourceUrl(fileUrl);
        correction.setCreatedAt(LocalDate.now());
        correction.setNumberOfDownload(0L);
        correction.setNumberOfView(0L);
        return correctionRepository.save(correction);
    }

    public void deleteCorrection(Long id) {
        correctionRepository.deleteById(id);
    }

    public Correction updateCorrection(Long id, Correction updatedCorrection, MultipartFile newFile) throws IOException {
        Correction existingCorrection = correctionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Correction not found with id " + id));

        // Mettre à jour le prix
        existingCorrection.setPrice(updatedCorrection.getPrice());

        // Mettre à jour l'exam si besoin (optionnel, selon logique métier)
        existingCorrection.setExam(updatedCorrection.getExam());

        // Si un nouveau fichier est uploadé, on remplace l'ancien fichier
        if (newFile != null && !newFile.isEmpty()) {
            // Supprimer ancien fichier
            if (existingCorrection.getResourceUrl() != null) {
                resourceService.deleteFile(existingCorrection.getResourceUrl());
            }

            // Enregistrer nouveau fichier
            String newResourceUrl = resourceService.storeFile(newFile, "corrections");
            existingCorrection.setResourceUrl(newResourceUrl);
            existingCorrection.setSize(newFile.getSize());
        }

        return correctionRepository.save(existingCorrection);
    }

}
