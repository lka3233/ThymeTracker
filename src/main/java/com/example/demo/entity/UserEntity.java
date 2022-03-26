package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_users")
public class UserEntity extends AbstractEntity
{
    @Column
    String login;
    @Column
    String password;
    @Column
    String firstName;
    @Column
    String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    List<TaskEntity> tasks;

    public UserEntity()
    {
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
