package com.soyjoctan.tests.bsidetest.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "task_name")
    @Size(min = 2, max = 50)
    String taskName;

    @Column(name = "finish_date")
    @NotNull
    @FutureOrPresent
    Date finishDate;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "student_id")
    @JsonBackReference
    StudentEntity student;
}