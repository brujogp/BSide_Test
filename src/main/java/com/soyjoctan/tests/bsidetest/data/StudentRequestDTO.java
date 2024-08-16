package com.soyjoctan.tests.bsidetest.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentRequestDTO {
    private Long id;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(Long id, String firstName, String lastName, String email, int age, List<TaskRequestDTO> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.tasks = tasks;
    }

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private int age;
    private List<TaskRequestDTO> tasks;
}