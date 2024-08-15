package com.soyjoctan.tests.bsidetest.services;

import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.data.repositories.StudentsRepository;
import com.soyjoctan.tests.bsidetest.expections.customs.NotFoundException;
import com.soyjoctan.tests.bsidetest.services.contracts.IStudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    StudentsRepository repository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.repository = studentsRepository;
    }

    @Transactional
    @Override
    public StudentEntity createNewStudent(StudentEntity newStudent) {
        return this.repository.save(newStudent);
    }

    @Override
    public StudentEntity getStudent(Long studentId) throws NotFoundException {
        return this.repository.findById(studentId).orElseThrow(() -> new NotFoundException("No se encontró el alúmno con el ID: ".concat(studentId.toString())));
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        return (List<StudentEntity>) this.repository.findAll();
    }

    @Transactional
    @Override
    public void deleteStudent(Long studentId) throws NotFoundException {
        try {
            this.repository.deleteById(studentId);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró el alúmno con el ID: ".concat(studentId.toString()));
        }
    }

    @Transactional
    @Override
    public StudentEntity updateStudent(StudentEntity student) {
        return this.repository.save(student);
    }
}
