package com.example.demo.services;

import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.IllegalTaskTitleException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.TaskModel;
import com.example.demo.repository.TaskDao;
import com.example.demo.repository.UserDao;
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
    @Autowired
    private UserDao userDao;

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

    public TaskModel createTask(TaskEntity entity, Long userId) throws IllegalTaskTitleException, UserNotFoundException
    {
        //Check if user exists and title is not empty
        Optional<UserEntity> container = userDao.findById(userId);
        if (!container.isPresent())
        {
            throw new UserNotFoundException("Пользователь не найден");
        }
        if (entity.getTitle().isEmpty())
        {
            throw new IllegalTaskTitleException("У задачи не указан title");
        }
        //Saving entity
        try
        {
            entity.setUserId(container.get());
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
    public TaskModel updateTask(TaskEntity entity, Long id) throws TaskNotFoundException
    {
        //Check if task exists
        Optional<TaskEntity> container = taskDao.findById(id);
        if (!container.isPresent())
        {
            throw new TaskNotFoundException("Запись не найдена");
        }
        TaskEntity currentTask = container.get();
        fillAtributes(currentTask, entity);
        //Saving entity
        try
        {
            taskDao.save(currentTask);
            return TaskModel.toModel(currentTask);
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

    /**
     * Fill attributes from client's data
     * @param currentTask
     * @param entity
     */
    private void fillAtributes(TaskEntity currentTask, TaskEntity entity)
    {
        currentTask.setTitle(entity.getTitle());
        currentTask.setState(entity.getState());
    }
}
