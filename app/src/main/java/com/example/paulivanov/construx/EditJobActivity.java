package com.example.paulivanov.construx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditJobActivity extends AppCompatActivity {

    private List<Estimate> estimates;
    private RecyclerView editJobRv;
    private long jobId;
    TextView jobName;
    TextView jobAddress;
    TextView startDate;
    TextView jobStatus;
    Button viewNotesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jobName = (TextView) findViewById(R.id.editJobName);
        jobAddress = (TextView) findViewById(R.id.editJobAddress);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        startDate = (TextView) findViewById(R.id.editJobStartDate);
        jobStatus = (TextView) findViewById(R.id.editJobStatus);
        viewNotesButton = (Button) findViewById(R.id.view_notes_button);

        jobId = getIntent().getLongExtra("job_id", 0);
        if(jobId == 0)
        {
            try {
                throw new Exception("JobId not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Job job = Job.findById(Job.class, jobId);
        jobName.setText(job.getJobName());
        jobAddress.setText(job.getAddress());
        startDate.setText(dateFormat.format(job.getStartDate()));
        jobStatus.setText(job.getCurrentStatus().toString().replace('_', ' '));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating new Estimate", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(EditJobActivity.this, CreateEstimateActivity.class);
                intent.putExtra("job_id", jobId);
                finish();
                startActivity(intent);
            }
        });
        viewNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to Job Notes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(EditJobActivity.this, JobNotesListActivity.class);
                intent.putExtra("job_id", jobId);
                finish();
                startActivity(intent);
            }
        });

        editJobRv =(RecyclerView)findViewById(R.id.editJobRv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        editJobRv.setLayoutManager(llm);
        editJobRv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        try
        {
            List<Estimate> newEstimates = Estimate.find(Estimate.class, "1=1");
            estimates = new ArrayList<>();
            for(Estimate est : newEstimates)
            {
                try
                {
                if(est.getJob().getId() == jobId) {
                    estimates.add(est);
                }
                }
                catch(Exception ex)
                {
                    est.delete();
                }

            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    private void initializeAdapter(){
        EstimateRVAdapter adapter = new EstimateRVAdapter(estimates);
        editJobRv.setAdapter(adapter);
    }
}
