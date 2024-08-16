package com.soyjoctan.tests.bsidetest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.services.contracts.IStudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentsController.class)
class StudentsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IStudentService studentService;

    static StudentEntity mockEntityStudentWithoutTasks;
    static StudentRequestDTO mockDtoStudentWithoutTasks;

    @BeforeAll
    static void setUp() {
        mockEntityStudentWithoutTasks = new StudentEntity();
        mockEntityStudentWithoutTasks.setEmail("pedroparamo@gmail.com");
        mockEntityStudentWithoutTasks.setFirstName("Casaverde");
        mockEntityStudentWithoutTasks.setLastName("Contrera");
        mockEntityStudentWithoutTasks.setId(1L);
        mockEntityStudentWithoutTasks.setAge(40);
        mockEntityStudentWithoutTasks.setTasks(new ArrayList<>());

        mockDtoStudentWithoutTasks = new StudentRequestDTO(
                null,
                "Casaverde",
                "Contrera",
                "pedroparamo@gmail.com",
                40,
                null
        );
    }

    @Test
    @DisplayName("Testing GET method to get all students")
    void getAllStudent() throws Exception {

        ArrayList<StudentEntity> list = new ArrayList<>();
        list.add(mockEntityStudentWithoutTasks);

        when(studentService.getAllStudent()).thenReturn(list);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"Casaverde\",\"lastName\":\"Contrera\",\"age\":40,\"email\":\"pedroparamo@gmail.com\",\"tasks\":[]}]"));
    }

    @Test
    @DisplayName("Testing GET method with ID")
    void getStudentById() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(mockEntityStudentWithoutTasks);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"firstName\":\"Casaverde\",\"lastName\":\"Contrera\",\"age\":40,\"email\":\"pedroparamo@gmail.com\",\"tasks\":[]}"));
    }

    @Test
    void createNewStudent() throws Exception {
        when(studentService.createNewStudent(mockDtoStudentWithoutTasks)).thenReturn(mockEntityStudentWithoutTasks);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockEntityStudentWithoutTasks);

        mockMvc.perform(
                        post("/students")
                                .content(json)
                                .characterEncoding("utf-8")
                                .contentType("application/json")
                )
                .andExpect(status().isCreated());
    }

    @Test
    void updateStudentInfo() throws Exception {
        when(studentService.updateStudent(mockDtoStudentWithoutTasks)).thenReturn(mockEntityStudentWithoutTasks);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockEntityStudentWithoutTasks);

        mockMvc.perform(
                        put("/students")
                                .content(json)
                                .characterEncoding("utf-8")
                                .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudentInfo() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudent(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockEntityStudentWithoutTasks);

        mockMvc.perform(
                        delete("/students/1")
                                .content(json)
                                .characterEncoding("utf-8")
                                .contentType("application/json")
                )
                .andExpect(status().isOk());
    }
}