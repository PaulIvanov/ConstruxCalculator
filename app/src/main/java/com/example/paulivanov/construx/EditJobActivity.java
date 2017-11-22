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
import android.widget.TextView;

import java.util.List;

public class EditJobActivity extends AppCompatActivity {

    private List<Estimate> estimates;
    private RecyclerView editJobRv;
    private long jobId;
    TextView jobName;
    TextView jobAddress;
    TextView startDate;
    TextView jobstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        jobId = getIntent().getLongExtra("job_id", 0);
        if(jobId == 0)
        {
            try {
                throw new Exception("JobId not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating new Estimate", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(EditJobActivity.this, CreateEstimateActivity.class);
                intent.putExtra("job_id", jobId);
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
            String userId = LoginActivity.CURRENT_USER.getId().toString();
            estimates = Estimate.find(Estimate.class, "1=1");

            //TODO: Filter Estimates list here to remove non user stuff
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
