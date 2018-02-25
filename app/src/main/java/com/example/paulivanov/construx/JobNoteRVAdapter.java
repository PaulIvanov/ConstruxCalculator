package com.example.paulivanov.construx;

/**
 * Created by pauli on 2/24/2018.
 */

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
public class JobNoteRVAdapter extends RecyclerView.Adapter<JobNoteRVAdapter.JobNoteViewHolder> {

    public static class JobNoteViewHolder extends RecyclerView.ViewHolder {

        CardView jobNoteCv;
        long noteId;
        long jobId;
        TextView noteTitle;
        TextView noteText;

        JobNoteViewHolder(final View itemView) {
            super(itemView);
            jobNoteCv = (CardView)itemView.findViewById(R.id.job_note_cardview);

            jobNoteCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Going to Note Editor", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent intent = new Intent(view.getContext(), AddJobNoteActivity.class);
                    intent.putExtra("note_id", noteId);
                    intent.putExtra("job_id", noteId);
                    itemView.getContext().startActivity(intent);
                }
            });
            jobNoteCv.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view){
                    JobNote estToDelete = JobNote.findById(JobNote.class, noteId);
                    boolean result = estToDelete.delete();
                    view.refreshDrawableState();
                    return result;
                }
            });

            noteTitle = (TextView)itemView.findViewById(R.id.job_note_title);
            noteText = (TextView)itemView.findViewById(R.id.job_note_description);
        }
    }

    List<JobNote> JobNotes;

    JobNoteRVAdapter(List<JobNote> JobNotes){
        this.JobNotes = JobNotes;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public JobNoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_job_note_card_view, viewGroup, false);
        return new JobNoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobNoteViewHolder JobNoteViewHolder, int i) {
        JobNote jn = JobNotes.get(i);
        JobNoteViewHolder.noteTitle.setText(jn.getTitle());
        JobNoteViewHolder.noteText.setText(jn.getDescription());
        JobNoteViewHolder.noteId = jn.getId();
        JobNoteViewHolder.jobId = jn.getJob().getId();
    }

    @Override
    public int getItemCount() {
        return JobNotes.size();
    }
}