package com.soyjoctan.tests.bsidetest.services.contracts;

import com.soyjoctan.tests.bsidetest.data.TaskRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;

public interface ITaskService {
    ResponseEntity<?> createNewTask(TaskRequestDTO task);
}
