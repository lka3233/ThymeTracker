package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tasks")
public class TaskEntity extends AbstractEntity
{
    @Column
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    UserEntity userId;

    @Column
    Date startDate;

    @Column
    Date endDate;

    @Column
    Long duration;

    public TaskEntity()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public UserEntity getUserId()
    {
        return userId;
    }

    public void setUserId(UserEntity userId)
    {
        this.userId = userId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }
}

