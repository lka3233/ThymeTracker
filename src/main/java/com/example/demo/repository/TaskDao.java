package com.example.demo.repository;

import com.example.demo.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<TaskEntity, Long>
{
}
