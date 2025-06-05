package com.LDE.monFax_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

    @Data
    @Builder
    @AllArgsConstructor
    @Entity
    public class Correction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private Double price;
        private String resourceUrl;

        @CreatedDate
        private LocalDate createdAt;
    }
