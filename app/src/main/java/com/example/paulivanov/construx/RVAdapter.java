package com.example.paulivanov.construx;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
        JobViewHolder.jobPhoto.setImageResource(R.drawable.house);
        JobViewHolder.job_id = Jobs.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return Jobs.size();
    }
}
