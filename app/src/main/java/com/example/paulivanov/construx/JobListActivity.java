package com.example.paulivanov.construx;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity {

    private List<Job> jobs;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to Create Job Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(JobListActivity.this, CreateJobActivity.class);
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton optionsMenu = (FloatingActionButton) findViewById(R.id.optionsButton);
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to options Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(JobListActivity.this, SettingActivity.class);
                finish();
                startActivity(intent);
            }
        });

        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        try
        {
            setAllJobsToCurrentUser();
            if(Material.count(Material.class) == 0)
            {
                initializeMaterials();
            }

        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(jobs);
        rv.setAdapter(adapter);
    }

    private void setAllJobsToCurrentUser(){
        long userId = LoginActivity.CURRENT_USER.getId();
        List<Job> newJobs = Job.find(Job.class, "1=1");
        jobs = new ArrayList<>();
        for(Job j : newJobs)
        {
            if(j.getUser() == null)
            {
                //Shouldnt really ever happen \_0_/
                j.setUser(LoginActivity.CURRENT_USER);
                j.save();
                j.delete();
            }
            if(j.getUser().getId() == userId) {
                jobs.add(j);
            }
        }
    }

    private void initializeMaterials()
    {
        List<String> materials = new ArrayList<>();
        materials.add("Cedar 5\"");
        materials.add("Hardi-plank 4\"");
        materials.add("Hardi-plank 5\"");
        materials.add("Hardi-plank 6\"");
        materials.add("Hardi-plank 7\"");
        materials.add("Hardi-panel 8'");
        materials.add("Hardi-panel 10'");
        materials.add("Hardi-shingle");


        for(String m : materials)
        {
            Material myMat = new Material();
            myMat.setMaterialName(m);
            myMat.setUnitOfMeasure("sq. ft");
            myMat.save();
        }
    }
}