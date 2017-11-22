package com.example.paulivanov.construx;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class CreateEstimateActivity extends AppCompatActivity {
    private long jobId;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_estimate);

        jobId = getIntent().getLongExtra("job_id", 0);
        job = Job.findById(Job.class, jobId);
        Button createButton = (Button) findViewById(R.id.create_estimate_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Estimate newEstimate = new Estimate();
                EditText estimateName = (EditText) findViewById(R.id.estimate_name_text_edit);
                TextView estimatePrice = (TextView) findViewById(R.id.estimate_price);

                newEstimate.setEstimatesName(estimateName.getText().toString());
                double price = 0.00;

                newEstimate.setTotalPrice(((long) Math.ceil(100 * price)));
                newEstimate.setJob(job);
                newEstimate.setCreationDate(new Date());
                try{
                    newEstimate.save();
                    Snackbar.make(view, "Estimate Created: ID# " + newEstimate.getId().toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //TODO: Return to EstimateList
                }
                catch(Exception ex)
                {
                    Snackbar.make(view, "Estimate Creation Failed: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
