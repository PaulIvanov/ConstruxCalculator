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

import java.text.NumberFormat;
import java.util.List;

public class EstimateRVAdapter extends RecyclerView.Adapter<EstimateRVAdapter.EstimateViewHolder> {

    public static class EstimateViewHolder extends RecyclerView.ViewHolder {

        CardView estimateCv;
        long estimateId;
        long jobId;
        TextView estimateName;
        TextView estimatePrice;

        EstimateViewHolder(final View itemView) {
            super(itemView);
            estimateCv = (CardView)itemView.findViewById(R.id.estimate_cv);
            estimateCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Going to Edit Estimate Activity", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent intent = new Intent(view.getContext(), EditEstimateActivity.class);
                    intent.putExtra("job_id", jobId);
                    intent.putExtra("estimate_id", estimateId);
                    itemView.getContext().startActivity(intent);
                }
            });
            estimateCv.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view){
                    Estimate estToDelete = Estimate.findById(Estimate.class, estimateId);
                    boolean result = estToDelete.delete();
                    view.refreshDrawableState();
                    return result;
                }
            });

            estimateName = (TextView)itemView.findViewById(R.id.estimate_name);
            estimatePrice = (TextView)itemView.findViewById(R.id.estimate_price);
        }
    }

    List<Estimate> Estimates;

    EstimateRVAdapter(List<Estimate> Estimates){
        this.Estimates = Estimates;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EstimateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_estimate_card_view, viewGroup, false);
        return new EstimateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EstimateViewHolder EstimateViewHolder, int i) {
        Estimate est = Estimates.get(i);
        EstimateViewHolder.estimateName.setText(est.getEstimatesName());
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        EstimateViewHolder.estimatePrice.setText(currencyFormatter.format((est.calculateEstimateTotal())));
        EstimateViewHolder.estimateId = est.getId();
        EstimateViewHolder.jobId = est.getJob().getId();
    }

    @Override
    public int getItemCount() {
        return Estimates.size();
    }
}
