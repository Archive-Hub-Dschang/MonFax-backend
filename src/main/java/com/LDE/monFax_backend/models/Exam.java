package com.LDE.monFax_backend.models;

import com.LDE.monFax_backend.enumerations.ExamType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Entity
public class Exam  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ExamType type;

    private int year;
    @CreatedDate
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private Correction correction;

}
