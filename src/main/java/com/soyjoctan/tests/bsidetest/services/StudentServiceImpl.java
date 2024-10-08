package com.soyjoctan.tests.bsidetest.services;

import com.soyjoctan.tests.bsidetest.data.StudentRequestDTO;
import com.soyjoctan.tests.bsidetest.data.TaskRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
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
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

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
        return createStudentEntity(newStudent);
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
        if (!studentRepository.existsById(studentId)) {
            throw new NotFoundException("No se encontró el alúmno con el ID: ".concat(studentId.toString()));
        }

        this.studentRepository.deleteById(studentId);
    }

    @Transactional
    @Override
    public StudentEntity updateStudent(StudentRequestDTO student) throws NoSuppliedIdStudentException {
        Optional.ofNullable(student.id()).orElseThrow(() -> new NoSuppliedIdStudentException("Es necesario suministrar el ID de un estudiante existente"));
        return getExistingStudentEntity(student);
    }

    @Transactional
    protected StudentEntity createStudentEntity(StudentRequestDTO newStudentDto) {
        return savingStudentWithTasks(newStudentDto, new StudentEntity());
    }

    @Transactional
    protected StudentEntity getExistingStudentEntity(StudentRequestDTO newStudentDto) throws NotFoundException {
        StudentEntity studentEntity = studentRepository
                .findById(newStudentDto.id())
                .orElseThrow(() -> new NotFoundException("No se encontró el alúmno con el ID: ".concat(newStudentDto.id().toString())));

        return savingStudentWithTasks(newStudentDto, studentEntity);
    }

    @Transactional
    protected StudentEntity savingStudentWithTasks(StudentRequestDTO newStudentDto, StudentEntity studentEntity) {
        studentEntity.setFirstName(newStudentDto.firstName());
        studentEntity.setLastName(newStudentDto.lastName());
        studentEntity.setAge(newStudentDto.age());
        studentEntity.setEmail(newStudentDto.email());
        studentEntity.setId(newStudentDto.id());

        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        Optional<List<TaskRequestDTO>> optionalTaskListsDto = Optional.ofNullable(newStudentDto.tasks());
        optionalTaskListsDto.ifPresent(tasks -> {
            tasksRepository.saveAll(tasks.stream()
                    .map(itemTask -> TaskServiceImpl.mapTasks(itemTask, savedStudentEntity))
                    .toList());

        });

        return savedStudentEntity;
    }
}
