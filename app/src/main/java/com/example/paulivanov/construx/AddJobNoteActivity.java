package com.example.paulivanov.construx;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class AddJobNoteActivity extends AppCompatActivity {
    long jobId;
    Job job;
    TextView title;
    TextView noteText;
    Button addNoteButton;
    long noteId;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddJobNoteActivity.this, JobNotesListActivity.class);
        intent.putExtra("job_id", jobId);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_note);

        jobId = getIntent().getLongExtra("job_id", 0);
        noteId = getIntent().getLongExtra("note_id", 0);
        job = Job.findById(Job.class, jobId);
        if(noteId != 0){
            JobNote note = JobNote.findById(JobNote.class, noteId);
            title = (EditText) findViewById(R.id.note_title_edit);
            title.setText(note.getTitle());
            noteText = (EditText) findViewById(R.id.note_desc_edit);
            noteText.setText(note.getDescription());
        }

        addNoteButton = (Button) findViewById(R.id.add_note_submit_button);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = (EditText) findViewById(R.id.note_title_edit);
                noteText = (EditText) findViewById(R.id.note_desc_edit);
                try{
                    JobNote newJobNote =  noteId == 0 ? new JobNote() : JobNote.findById(JobNote.class, noteId);
                    newJobNote.setJob(job);
                    newJobNote.setTitle(title.getText().toString());
                    newJobNote.setDescription(noteText.getText().toString());
                    newJobNote.setDateCreated(new Date());

                    newJobNote.save();
                    Snackbar.make(view, "Added new Note", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent intent = new Intent(view.getContext(), JobNotesListActivity.class);
                    intent.putExtra("job_id", jobId);
                    finish();
                    view.getContext().startActivity(intent);
                }
                catch(Exception ex)
                {
                    Snackbar.make(view, "Something went wrong." + ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
    }
