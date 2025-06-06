package com.LDE.monFax_backend.services;


import com.LDE.monFax_backend.enumerations.ExamType;
import com.LDE.monFax_backend.models.Exam;
import com.LDE.monFax_backend.models.Subject;
import com.LDE.monFax_backend.repositories.ExamRepository;
import com.LDE.monFax_backend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final ResourceService resourceService;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long id) {
        Optional<Exam> exam =  examRepository.findById(id);
        exam.ifPresent(foundExam -> {
            resourceService.increaseNumberOfViews(foundExam);
            examRepository.save(foundExam);
        });
        return exam;
    }

    public Exam createExam(String title, String type, int year, Long subjectId, MultipartFile file) throws IOException {
        // 1. Upload du fichier
        String fileUrl = resourceService.storeFile(file, "exams");

        // 2. Récupérer la matière
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Sujet introuvable avec l'id : " + subjectId));

        // 3. Créer et sauvegarder l'examen
        Exam exam = new Exam();
        exam.setTitle(title);
        exam.setType(ExamType.valueOf(type.toUpperCase()));
        exam.setYear(year);
        exam.setSubject(subject);
        exam.setResourceUrl(fileUrl);
        exam.setCreatedAt(LocalDate.now());
        exam.setNumberOfDownload(0L);
        exam.setNumberOfView(0L);

        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    public Exam updateExam(Long id, Exam updatedExam, MultipartFile newFile) throws IOException {
        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id " + id));

        // Mise à jour des champs simples
        existingExam.setTitle(updatedExam.getTitle());
        existingExam.setType(updatedExam.getType());
        existingExam.setYear(updatedExam.getYear());
        existingExam.setSubject(updatedExam.getSubject());

        // Si un nouveau fichier est uploadé, on remplace l'ancien fichier
        if (newFile != null && !newFile.isEmpty()) {
            // Supprimer l'ancien fichier physique
            if (existingExam.getResourceUrl() != null) {
                resourceService.deleteFile(existingExam.getResourceUrl());
            }

            // Enregistrer le nouveau fichier et mettre à jour resourceUrl et size
            String newResourceUrl = resourceService.storeFile(newFile,"exams");
            existingExam.setResourceUrl(newResourceUrl);
            existingExam.setSize(newFile.getSize());
        }

        return examRepository.save(existingExam);
    }

}
