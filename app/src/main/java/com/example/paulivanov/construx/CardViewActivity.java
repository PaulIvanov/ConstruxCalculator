package com.example.paulivanov.construx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends AppCompatActivity {

    TextView jobName;
    TextView jobId;
    ImageView jobPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_view);
        jobName = (TextView)findViewById(R.id.job_name);
        jobId = (TextView)findViewById(R.id.job_id);
        jobPhoto = (ImageView)findViewById(R.id.job_photo);

        jobName.setText("Emma Wilson");
        jobId.setText("23 years old");
        jobPhoto.setImageResource(R.drawable.emma);

    }

}
