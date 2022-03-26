package com.example.demo.controller;

import com.example.demo.entity.TaskEntity;
import com.example.demo.exceptions.IllegalTaskTitleException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity getTask(@RequestParam Long id)
    {
        try
        {
            return ResponseEntity.ok().body(taskService.getTask(id));
        }
        catch (TaskNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskEntity entity)
    {
        try
        {
            taskService.createTask(entity);
            return ResponseEntity.ok("Задача сохранена");
        }
        catch (IllegalTaskTitleException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Внутренняя ошибка: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok().body(taskService.deleteTask(id));
        }
        catch (EmptyResultDataAccessException e)
        {
            return ResponseEntity.badRequest().body("Запись не найдена в базе");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
