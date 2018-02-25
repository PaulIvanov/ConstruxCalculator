package com.example.paulivanov.construx;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class JobNotesListActivity extends AppCompatActivity {
    private List<JobNote> jobNotes;
    private RecyclerView jobNotesRv;
    private long jobId;
    private Job job;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(JobNotesListActivity.this, EditJobActivity.class);
        intent.putExtra("job_id", jobId);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_notes_list);

        jobId = getIntent().getLongExtra("job_id", 0);
        job = Job.findById(Job.class, jobId);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_note_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Going to Add a Note Page", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(JobNotesListActivity.this, AddJobNoteActivity.class);
                intent.putExtra("job_id", jobId);
                finish();
                startActivity(intent);
            }
        });
        jobNotesRv =(RecyclerView)findViewById(R.id.note_rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        jobNotesRv.setLayoutManager(llm);
        jobNotesRv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        try
        {
            List<JobNote> newJobNotes = JobNote.find(JobNote.class, "1=1");
            jobNotes = new ArrayList<>();
            for(JobNote note : newJobNotes)
            {
                try
                {
                    if(note.getJob().getId() == jobId) {
                        jobNotes.add(note);
                    }
                }
                catch(Exception ex)
                {
                    note.delete();
                }

            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    private void initializeAdapter(){
        JobNoteRVAdapter adapter = new JobNoteRVAdapter(jobNotes);
        jobNotesRv.setAdapter(adapter);
    }
}
