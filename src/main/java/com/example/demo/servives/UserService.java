package com.example.demo.servives;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException
    {
        String login = user.getLogin();
        if (null != userDao.findByLogin(login))
        {
            String message = String.format("Пользователь с логином %s уже существует", login);
            throw new UserAlreadyExistsException(message);
        }
        else
        {
            return userDao.save(user);
        }
    }
    public UserModel getUser(Long id) throws UserNotFoundException
    {
        UserEntity entity = null;
        try
        {
            entity = userDao.findById(id).get();
        }
        catch (Exception e)
        {
            logger.error("Something wrong: ", e);
        }
        if (null == entity)
        {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserModel.toModel(entity);
    }

    public Long deleteUser(Long id)
    {
        Long result = -1L;
        try
        {
            userDao.deleteById(id);
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
