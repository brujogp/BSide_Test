package com.soyjoctan.tests.bsidetest.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StudentRequestDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("age") int age,
        @JsonProperty("tasks") List<TaskRequestDTO> tasks
) {
}