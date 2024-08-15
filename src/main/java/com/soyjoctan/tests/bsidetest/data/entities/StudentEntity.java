package com.soyjoctan.tests.bsidetest.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    String lastName;

    @Column(name = "age")
    int age;
}
