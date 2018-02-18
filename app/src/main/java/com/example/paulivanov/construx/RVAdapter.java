package com.example.paulivanov.construx;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.JobViewHolder> {

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView jobName;
        TextView jobAddress;
        TextView jobStatus;
        TextView jobDateCreated;
        private long job_id;
        ImageView jobPhoto;

        JobViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Going to Edit Job Activity", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent intent = new Intent(view.getContext(), EditJobActivity.class);
                    intent.putExtra("job_id", job_id);
                    itemView.getContext().startActivity(intent);
                }
            });

            cv.setOnLongClickListener(new OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view){
                    Job jobToDelete = Job.findById(Job.class, job_id);
                    boolean result = jobToDelete.delete();
                    view.refreshDrawableState();
                    return result;
               }
            });

            jobName = (TextView)itemView.findViewById(R.id.job_name);
            jobAddress = (TextView)itemView.findViewById(R.id.job_address);
            jobStatus = (TextView)itemView.findViewById(R.id.job_status_view);
            jobPhoto = (ImageView)itemView.findViewById(R.id.job_photo);
            jobDateCreated = (TextView)itemView.findViewById(R.id.job_date_created);
        }
    }

    List<Job> Jobs;

    RVAdapter(List<Job> Jobs){
        this.Jobs = Jobs;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        JobViewHolder jvh = new JobViewHolder(v);
        return jvh;
    }

    @Override
    public void onBindViewHolder(JobViewHolder JobViewHolder, int i) {
        JobViewHolder.jobName.setText(Jobs.get(i).getJobName());
        JobViewHolder.jobAddress.setText(Jobs.get(i).getAddress().toString());
        JobStatus js = Jobs.get(i).getCurrentStatus();
        if(js != null){
        JobViewHolder.jobStatus.setText(js.toString().replace('_', ' '));
        }
        JobViewHolder.jobPhoto.setImageResource(R.drawable.house);
        JobViewHolder.jobDateCreated.setText("Created: " + String.format(Jobs.get(i).getStartDate().toString(), "dd-MM-yyyy"));
        JobViewHolder.job_id = Jobs.get(i).getId();

    }

    @Override
    public int getItemCount() {
        return Jobs.size();
    }
}
