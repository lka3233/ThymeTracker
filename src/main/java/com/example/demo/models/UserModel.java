package com.example.demo.models;

import com.example.demo.entity.UserEntity;


public class UserModel
{
    private Long id;
    private String login;
    private String firstName;
    private String lastName;

    UserModel(UserEntity entity)
    {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
    }

    public static UserModel toModel(UserEntity entity)
    {
        return null != entity ? new UserModel(entity) : null;
    }

    public Long getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
}
