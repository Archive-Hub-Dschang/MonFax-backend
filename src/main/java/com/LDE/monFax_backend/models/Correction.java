package com.LDE.monFax_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Correction extends Resource{

        private Double price;

        private String resourceUrl;


        @OneToOne
        @JoinColumn(name = "exam_id", referencedColumnName = "id")
        private Exam exam;

        @OneToMany(mappedBy = "correction", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Payment> payments = new ArrayList<>();




    }
