package com.example.paulivanov.construx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JobNoteCardViewActivity extends AppCompatActivity {
    TextView JobNoteTitle;
    TextView JobNoteDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_note_card_view);
    }
}
