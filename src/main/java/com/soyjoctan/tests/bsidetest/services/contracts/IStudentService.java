package com.soyjoctan.tests.bsidetest.services.contracts;

import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentService {
    StudentEntity createNewStudent(StudentRequestDTO newStudent);

    List<StudentEntity> getAllStudent();

    StudentEntity getStudent(Long studentId);

    void deleteStudent(Long studentId);

    StudentEntity updateStudent(StudentRequestDTO student);
}
