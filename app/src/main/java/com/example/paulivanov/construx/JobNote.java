package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

import java.util.Date;

/**
 * Created by pauli on 2/24/2018.
 */

public class JobNote extends SugarRecord {

    @NotNull
    private Job job;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Date dateCreated;

    public JobNote() {
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
