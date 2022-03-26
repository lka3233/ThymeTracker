package com.example.demo.services;

import com.example.demo.entity.TaskEntity;
import com.example.demo.exceptions.IllegalTaskTitleException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.models.TaskModel;
import com.example.demo.repository.TaskDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService
{
    @Autowired
    private TaskDao taskDao;

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskModel getTask(Long id) throws TaskNotFoundException
    {
        Optional<TaskEntity> container = taskDao.findById(id);
        if (container.isPresent())
        {
            return TaskModel.toModel(container.get());
        }
        else
        {
            throw new TaskNotFoundException("Задача не найдена");
        }
    }

    public TaskModel createTask(TaskEntity entity) throws IllegalTaskTitleException
    {
        if (entity.getTitle().isEmpty())
        {
            throw new IllegalTaskTitleException("У задачи не указан title");
        }
        try
        {
            taskDao.save(entity);
            return TaskModel.toModel(entity);
        }
        catch (Exception e)
        {
            String message = String.format("Произошла ошибка при сохранении задачи %s",entity.getTitle());
            logger.error(message,e);
            throw e;
        }
    }

    public Long deleteTask(Long id)
    {
        Long result = -1L;
        try
        {
            taskDao.deleteById(id);
            result = id;
        }
        catch (Exception e)
        {
            logger.error("Error while deleting", e);
            throw e;
        }
        return result;
    }
}
