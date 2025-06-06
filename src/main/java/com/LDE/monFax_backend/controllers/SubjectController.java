package com.LDE.monFax_backend.controllers;

import com.LDE.monFax_backend.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    @GetMapping("/count")
    public ResponseEntity<Long> getSubjectCount() {
        return ResponseEntity.ok(subjectService.getTotalSubjects());
    }
}
