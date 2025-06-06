package com.LDE.monFax_backend.services;

import com.LDE.monFax_backend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public long getTotalSubjects() {
        return subjectRepository.count();
    }
}
