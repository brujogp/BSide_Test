package com.soyjoctan.tests.bsidetest.data.repositories;

import com.soyjoctan.tests.bsidetest.data.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<StudentEntity, Long> {
}
