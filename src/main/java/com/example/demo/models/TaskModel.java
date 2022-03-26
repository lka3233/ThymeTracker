package com.example.demo.models;

import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.TaskEntity.State;
import java.util.Date;

public class TaskModel
{
    private Long id;
    private String title;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private Long duration;
    private State state;

    public State getState()
    {
        return state;
    }

    TaskModel(TaskEntity entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.duration = entity.getDuration();
        this.endDate = entity.getEndDate();
        this.startDate = entity.getStartDate();
        if (entity.getUserId() != null)
        {
            this.userId = entity.getUserId().getId();
        }
        this.state = entity.getState();
    }

    public static TaskModel toModel(TaskEntity entity)
    {
        return new TaskModel(entity);
    }

    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Long getUserId()
    {
        return userId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public Long getDuration()
    {
        return duration;
    }
}
