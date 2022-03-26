package com.example.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;

@MappedSuperclass
public class AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    public Long getId()
    {
        return id;
    }
}
