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

public class EstimateRVAdapter extends RecyclerView.Adapter<EstimateRVAdapter.EstimateViewHolder> {

    public static class EstimateViewHolder extends RecyclerView.ViewHolder {

        CardView estimateCv;
        long estimateId;
        TextView estimateName;
        TextView estimatePrice;

        EstimateViewHolder(final View itemView) {
            super(itemView);
            estimateCv = (CardView)itemView.findViewById(R.id.estimate_cv);
            estimateCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Going to View Edit Activity", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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
        EstimateViewHolder.estimateName.setText(Estimates.get(i).getEstimatesName());
        EstimateViewHolder.estimatePrice.setText(Long.toString((Estimates.get(i).getTotalPrice())) + "$");
        EstimateViewHolder.estimateId = Estimates.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return Estimates.size();
    }
}
