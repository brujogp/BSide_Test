package com.soyjoctan.tests.bsidetest.services;

import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.data.repositories.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {
    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentsRepository studentRepository;
    private StudentEntity mockEntityStudentWithoutTasksAndId;
    private StudentRequestDTO mockDtoStudentWithoutTasks;
    private StudentEntity mockEntityStudentWithoutTasksButWithId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEntityStudentWithoutTasksAndId = new StudentEntity();
        mockEntityStudentWithoutTasksAndId.setEmail("pedroparamo@gmail.com");
        mockEntityStudentWithoutTasksAndId.setFirstName("Casaverde");
        mockEntityStudentWithoutTasksAndId.setLastName("Contrera");
        mockEntityStudentWithoutTasksAndId.setAge(40);
        mockEntityStudentWithoutTasksAndId.setTasks(new ArrayList<>());

        mockEntityStudentWithoutTasksButWithId = new StudentEntity();
        mockEntityStudentWithoutTasksButWithId.setEmail("pedroparamo@gmail.com");
        mockEntityStudentWithoutTasksButWithId.setFirstName("Casaverde");
        mockEntityStudentWithoutTasksButWithId.setLastName("Contrera");
        mockEntityStudentWithoutTasksButWithId.setAge(40);
        mockEntityStudentWithoutTasksButWithId.setId(1L);
        mockEntityStudentWithoutTasksButWithId.setTasks(new ArrayList<>());

        mockDtoStudentWithoutTasks = new StudentRequestDTO(
                null,
                "Casaverde",
                "Contrera",
                "pedroparamo@gmail.com",
                40,
                null
        );
    }


    @DisplayName("Creating new student")
    @Test
    void testCreateNewStudent() {
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(mockEntityStudentWithoutTasksButWithId);

        StudentEntity result = studentService.createNewStudent(mockDtoStudentWithoutTasks);

        assertEquals(mockEntityStudentWithoutTasksButWithId.getFirstName(), result.getFirstName());

        verify(studentRepository, times(1)).save(any(StudentEntity.class));
    }

    @Test
    @DisplayName("Get student by ID")
    void getStudent() {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(mockEntityStudentWithoutTasksAndId));

        StudentEntity result = studentService.getStudent(1L);

        assertEquals(mockEntityStudentWithoutTasksButWithId.getFirstName(), result.getFirstName());
    }

    @Test
    @DisplayName("Get students list")
    void getAllStudent() {
    }

    @Test
    @DisplayName("Delete student by ID")
    void deleteStudent() {
    }

    @Test
    @DisplayName("Update student by ID")
    void updateStudent() {
    }

    @Test
    void createStudentEntity() {
    }

    @Test
    void getExistingStudentEntity() {
    }

    @Test
    void savingStudentWithTasks() {
    }
}