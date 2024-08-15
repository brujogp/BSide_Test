package com.soyjoctan.tests.bsidetest.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @Column(name = "task_name")
    String taskName;

    @Column(name = "finish_date")
    Date finishDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    StudentEntity student;
}