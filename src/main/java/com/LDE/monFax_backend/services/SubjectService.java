package com.LDE.monFax_backend.services;

import com.LDE.monFax_backend.models.Subject;
import com.LDE.monFax_backend.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> updateSubject(Long id, Subject subjectDetails) {
        return subjectRepository.findById(id).map(subject -> {
            subject.setName(subjectDetails.getName());
            return subjectRepository.save(subject);
        });
    }

    public boolean deleteSubject(Long id) {
        return subjectRepository.findById(id).map(subject -> {
            subjectRepository.delete(subject);
            return true;
        }).orElse(false);
    }
}
