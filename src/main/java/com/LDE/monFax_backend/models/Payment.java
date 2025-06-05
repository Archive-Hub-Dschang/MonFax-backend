package com.LDE.monFax_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video ;

    @ManyToOne
    @JoinColumn(name = "correction_id")
    private Correction correction ;


    @ManyToOne
    @JoinColumn(name = "lectureCourse_id")
    private LectureCourse lectureCourse ;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester ;


    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject ;


}
