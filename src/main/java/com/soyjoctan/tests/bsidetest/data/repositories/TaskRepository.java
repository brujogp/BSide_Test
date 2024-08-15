package com.soyjoctan.tests.bsidetest.data.repositories;

import com.soyjoctan.tests.bsidetest.data.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}
