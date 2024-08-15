package com.soyjoctan.tests.bsidetest.services;

import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.data.TaskRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.data.entities.TaskEntity;
import com.soyjoctan.tests.bsidetest.data.repositories.StudentsRepository;
import com.soyjoctan.tests.bsidetest.data.repositories.TaskRepository;
import com.soyjoctan.tests.bsidetest.expections.customs.NoSuppliedIdStudentException;
import com.soyjoctan.tests.bsidetest.expections.customs.NotFoundException;
import com.soyjoctan.tests.bsidetest.services.contracts.IStudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    private final TaskRepository tasksRepository;
    StudentsRepository studentRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository, TaskRepository taskRepository) {
        this.studentRepository = studentsRepository;
        this.tasksRepository = taskRepository;
    }

    @Transactional
    @Override
    public StudentEntity createNewStudent(StudentRequestDTO newStudent) {
        return savingStudentWithTasks(newStudent);
    }

    @Override
    public StudentEntity getStudent(Long studentId) throws NotFoundException {
        return this.studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("No se encontró el alúmno con el ID: ".concat(studentId.toString())));
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        return (List<StudentEntity>) this.studentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteStudent(Long studentId) throws NotFoundException {
        try {
            this.studentRepository.deleteById(studentId);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró el alúmno con el ID: ".concat(studentId.toString()));
        }
    }

    @Transactional
    @Override
    public StudentEntity updateStudent(StudentRequestDTO student) throws NoSuppliedIdStudentException {
        Optional.ofNullable(student.getId()).orElseThrow(() -> new NoSuppliedIdStudentException("Es necesario suministrar el ID de un estudiante existente"));
        return savingStudentWithTasks(student);
    }

    private StudentEntity savingStudentWithTasks(StudentRequestDTO newStudentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(newStudentDto.getFirstName());
        studentEntity.setLastName(newStudentDto.getLastName());
        studentEntity.setAge(newStudentDto.getAge());
        studentEntity.setId(newStudentDto.getId());

        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        Optional<List<TaskRequestDTO>> optionalTaskListsDto = Optional.ofNullable(newStudentDto.getTasks());
        optionalTaskListsDto.ifPresent(tasks -> {
            tasksRepository.saveAll(tasks.stream()
                    .map(itemTask -> TaskServiceImpl.mapTasks(itemTask, savedStudentEntity))
                    .collect(Collectors.toList()));

        });

        return savedStudentEntity;
    }
}
