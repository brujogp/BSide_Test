package com.soyjoctan.tests.bsidetest.controllers;

import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.services.contracts.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/students")
@RestController
public class StudentsController {
    private final IStudentService studentService;

    public StudentsController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentInfo(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(this.studentService.getStudent(studentId));
    }

    @GetMapping(path = {"/", ""})
    public ResponseEntity<?> getAllStudents() {
        var response = this.studentService.getAllStudent();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createNewStudent(@RequestBody StudentRequestDTO student) {
        var response = this.studentService.createNewStudent(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateStudentInfo(@RequestBody StudentRequestDTO studentToUpdate) {
        return ResponseEntity.ok(this.studentService.updateStudent(studentToUpdate));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable("studentId") Long student) {
        this.studentService.deleteStudent(student);
        return ResponseEntity.ok().build();
    }
}