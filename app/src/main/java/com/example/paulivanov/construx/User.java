package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by PaulIvanov on 10/27/2017.
 */

public class User extends SugarRecord {
    @Unique
    @NotNull
    private String email;


    @NotNull
    private String password;

    private List<Job> jobs;

    public User()
    {
    }

    //User Registration
    public User(String email, String username, String password)
    {
        this.email = email;
        //this.username = username;
        this.password = password;
    }

    //User Login
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
