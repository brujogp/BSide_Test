package com.soyjoctan.tests.bsidetest.controllers;

import com.soyjoctan.tests.bsidetest.data.TaskRequestDTO;
import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import com.soyjoctan.tests.bsidetest.services.contracts.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private final ITaskService service;

    public TasksController(ITaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createNewStudent(@RequestBody TaskRequestDTO taskRequest) {
        return new ResponseEntity<>(this.service.createNewTask(taskRequest), HttpStatus.CREATED);
    }
}
