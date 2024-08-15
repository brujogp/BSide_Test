package com.soyjoctan.tests.bsidetest.services;

import com.soyjoctan.tests.bsidetest.data.TaskRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.data.entities.TaskEntity;
import com.soyjoctan.tests.bsidetest.data.repositories.TaskRepository;
import com.soyjoctan.tests.bsidetest.services.contracts.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> createNewTask(TaskRequestDTO task) {
        var taskToSave = mapTasks(task, null);
        return new ResponseEntity<>(repository.save(taskToSave), HttpStatus.CREATED);
    }

    static TaskEntity mapTasks(TaskRequestDTO task, StudentEntity student) {
        TaskEntity entity = new TaskEntity();
        entity.setTaskName(task.getTaskName());
        entity.setFinishDate(task.getFinishDate());
        entity.setStudent(student);
        return entity;
    }
}
