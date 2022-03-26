package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user)
    {
        
        try
        {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь сохранён");
        }
        catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body("Ошибка сохранения: " + e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Ошибка сохранения: " + e.getCause());
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id)
    {
        try
        {
            return ResponseEntity.ok().body(userService.getUser(id));
        }
        catch (UserNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok().body(userService.deleteUser(id));
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
