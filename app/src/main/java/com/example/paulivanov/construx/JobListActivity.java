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
            String userId = LoginActivity.CURRENT_USER.getId().toString();
            jobs = Job.find(Job.class, "1=1");

            //TODO: Filter jobs list here to remove non user stuff
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
}