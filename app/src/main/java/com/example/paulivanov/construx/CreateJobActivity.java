package com.example.paulivanov.construx;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class CreateJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        Button createButton = (Button) findViewById(R.id.createJobButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Job newJob = new Job();
                EditText jobName = (EditText) findViewById(R.id.jobNameTextEdit);
                EditText jobAddress = (EditText) findViewById(R.id.jobAddressEdit);

                newJob.setJobName(jobName.getText().toString());
                newJob.setAddress(jobAddress.getText().toString());
                newJob.setUser(LoginActivity.CURRENT_USER);
                newJob.setCurrentStatus(JobStatus.NO_ESTIMATE);
                newJob.setStartDate(new Date());
                try{
                newJob.save();
                    Snackbar.make(view, "Job Created: ID# " + newJob.getId().toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //TODO: Return to JobList
                }
                catch(Exception ex)
                {
                    Snackbar.make(view, "Job Creation Failed: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
}
