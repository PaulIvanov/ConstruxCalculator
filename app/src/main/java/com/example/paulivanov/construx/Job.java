package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Created by PaulIvanov on 10/27/2017.
 */

public class Job extends SugarRecord {

    @NotNull
    private User user;

    @NotNull
    private String jobName;

    @NotNull
    private String address;

    @NotNull
    private Date startDate;

    @NotNull
    private JobStatus currentStatus;

    private List<Estimate> estimates;
    //TODO: NOTES and PHOTOS

    public Job(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public JobStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(JobStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public List<Estimate> getEstimates() {
        return estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }
}
