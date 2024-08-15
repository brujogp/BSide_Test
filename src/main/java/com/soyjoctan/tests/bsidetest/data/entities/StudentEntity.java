package com.soyjoctan.tests.bsidetest.data.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    String firstName;

    @NotNull
    @Column(name = "last_name")
    String lastName;

    @NotNull
    @Max(130)
    @Min(18)
    @Column(name = "age")
    int age;

    @Column(name = "email")
    @NotNull
    @Email
    String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<TaskEntity> tasks;

}
