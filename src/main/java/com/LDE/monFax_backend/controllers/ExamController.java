package com.LDE.monFax_backend.controllers;


import com.LDE.monFax_backend.enumerations.ExamType;
import com.LDE.monFax_backend.models.Exam;
import com.LDE.monFax_backend.services.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exams", description = "API pour gérer les epreuves")

@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    @Operation(
            summary = "Lister tous les epreuves",
            description = "Récupère la liste complète des epreuves",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des epreuves récupérée",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Exam.class)))
            }
    )
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer une epreuve par ID",
            description = "Retourne une epreuve  correspondant à l'ID fourni",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Epreuve trouvée",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Exam.class))),
                    @ApiResponse(responseCode = "404", description = "Epreuve  non trouvée")
            })
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        return examService.getExamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Créer une nouvelle epreuve",
            description = "Crée une epreuve avec un fichier uploadé (pdf ou docx)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "epreuve créé avec succès"),
                    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur lors de la création)")
            }
    )
    public ResponseEntity<String> createExam(
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("year") int year,
            @RequestParam("subjectId") Long subjectId,
            @RequestParam("file") MultipartFile file) {
        try {
            Exam exam = examService.createExam(title, type, year, subjectId, file);
            return ResponseEntity.ok("creation faite avec success");
        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(
            @PathVariable Long id,
            @RequestPart("exam") Exam exam,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Exam updated = examService.updateExam(id, exam, file);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getExamsCount() {
        return ResponseEntity.ok(examService.getTotalExams());
    }

    @GetMapping("/count-by-type/{type}")
    public ResponseEntity<Long> getExamsCountByType(@PathVariable ExamType type) {
        return ResponseEntity.ok(examService.getExamsCountByType(type));
    }


}
