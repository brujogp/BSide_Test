package com.soyjoctan.tests.bsidetest.controllers;

import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
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

    @GetMapping
    public ResponseEntity<?> getAllStudent() {
        return ResponseEntity.ok(this.studentService.getAllStudent());
    }

    @PostMapping
    public ResponseEntity<?> createNewStudent(@RequestBody StudentEntity student) {
        return new ResponseEntity<>(this.studentService.createNewStudent(student), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> getStudentInfo(@RequestBody StudentEntity studentToUpdate) {
        return ResponseEntity.ok(this.studentService.updateStudent(studentToUpdate));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable("studentId") Long student) {
        this.studentService.deleteStudent(student);
        return ResponseEntity.ok().build();
    }
}