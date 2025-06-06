package com.LDE.monFax_backend.services;


import com.LDE.monFax_backend.models.LectureCourse;
import com.LDE.monFax_backend.models.Subject;
import com.LDE.monFax_backend.repositories.LectureCourseRepository;
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
public class LectureCourseService {

    private final LectureCourseRepository lectureCourseRepository;
    private final SubjectRepository subjectRepository;
    private final ResourceService resourceService;

    public List<LectureCourse> getAllCourses() {
        return lectureCourseRepository.findAll();
    }

    public Optional<LectureCourse> getCourseById(Long id) {
        return lectureCourseRepository.findById(id);
    }

    public LectureCourse createCourse(String title, String description, Double price, Long subjectId, MultipartFile file) throws IOException {
        String fileUrl = resourceService.storeFile(file, "courses");

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Matière introuvable avec l'id : " + subjectId));

        LectureCourse course = new LectureCourse();
        course.setTitle(title);
        course.setDescription(description);
        course.setPrice(price);
        course.setSubject(subject);
        course.setResourceUrl(fileUrl);
        course.setCreatedAt(LocalDate.now());
        course.setNumberOfDownload(0L);
        course.setNumberOfView(0L);

        return lectureCourseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        lectureCourseRepository.deleteById(id);
    }

    public LectureCourse updateCourse(Long id, LectureCourse lectureCourseDetails, MultipartFile file) throws IOException {
        LectureCourse lectureCourse = lectureCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("support de Cours  non trouvé"));

        lectureCourse.setTitle(lectureCourseDetails.getTitle());
        lectureCourse.setDescription(lectureCourseDetails.getDescription());
        lectureCourse.setPrice(lectureCourseDetails.getPrice());
        lectureCourse.setSubject(lectureCourseDetails.getSubject());

        if (file != null && !file.isEmpty()) {
            resourceService.deleteFile(lectureCourse.getResourceUrl());

            String fileName = resourceService.storeFile(file,"lectureCourse");
            lectureCourse.setResourceUrl(fileName);
            lectureCourse.setSize(file.getSize());
        }

        return lectureCourseRepository.save(lectureCourse);
    }

}
