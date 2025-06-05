package com.LDE.monFax_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
    @Data
    @Entity
    public class Correction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private Double price;

        private String resourceUrl;


        @OneToOne
        @JoinColumn(name = "exam_id", referencedColumnName = "id")
        private Exam exam;

    @OneToMany(mappedBy = "correction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();




    }
