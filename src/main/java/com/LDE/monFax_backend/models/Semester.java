package com.LDE.monFax_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects = new ArrayList<>();
}
