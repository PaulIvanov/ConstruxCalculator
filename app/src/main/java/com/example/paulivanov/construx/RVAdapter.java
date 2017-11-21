package com.example.paulivanov.construx;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.JobViewHolder> {

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView jobName;
        TextView jobId;
        ImageView jobPhoto;

        JobViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            jobName = (TextView)itemView.findViewById(R.id.job_name);
            jobId = (TextView)itemView.findViewById(R.id.job_id);
            jobPhoto = (ImageView)itemView.findViewById(R.id.job_photo);
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
        JobViewHolder.jobId.setText(Jobs.get(i).getId().toString());
        JobViewHolder.jobPhoto.setImageResource(R.drawable.emma);
    }

    @Override
    public int getItemCount() {
        return Jobs.size();
    }
}
