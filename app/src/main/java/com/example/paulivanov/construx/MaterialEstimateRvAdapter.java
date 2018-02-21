package com.example.paulivanov.construx;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PaulIvanov on 1/10/2018.
 */

public class MaterialEstimateRvAdapter extends RecyclerView.Adapter<MaterialEstimateRvAdapter.MaterialEstimateViewHolder>  {
    @Override
    public MaterialEstimateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_material_estimate_card_view,
                parent, false);
        return new MaterialEstimateRvAdapter.MaterialEstimateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MaterialEstimateViewHolder holder, int position) {
        MaterialEstimate est = MaterialEstimate.get(position);
        int materialTotalPrice = 0;
        int materialTotalMeas = 0;

        List<Measurement> measurements = Measurement.find(Measurement.class, "1=1");
        for(Measurement meas : measurements){
            try{
            if(meas.getMaterialEstimate().getId().equals(est.getId())){
                int totalMeas = meas.getLength() * meas.getLength();
                materialTotalPrice +=  totalMeas * est.getMaterialPrice();
                materialTotalMeas += totalMeas;
            }
            }
            catch(Exception ex)
            {
                meas.delete();
            }
        }
        String unitOfMeasure = " " + est.getMaterial().getUnitOfMeasure();
        holder.MaterialName.setText(est.getMaterial().getMaterialName());
        holder.MaterialPrice.setText("$" + Integer.toString((est.getMaterialPrice())) + unitOfMeasure);
        holder.MaterialTotalPrice.setText("Total: $"+ Integer.toString(materialTotalPrice));
        holder.MaterialTotalMeas.setText("Total: " + Integer.toString(materialTotalMeas) + unitOfMeasure);
    }

    @Override
    public int getItemCount() {
        return MaterialEstimate.size();
    }

    List<MaterialEstimate> MaterialEstimate;

    MaterialEstimateRvAdapter(List<MaterialEstimate> MaterialEstimate){
        this.MaterialEstimate = MaterialEstimate;
    }

    public static class MaterialEstimateViewHolder extends RecyclerView.ViewHolder {

        CardView estimateCv;
        long estimateId;
        long materialEstId;
        TextView MaterialName;
        TextView MaterialPrice;
        TextView MaterialTotalPrice;
        TextView MaterialTotalMeas;

        MaterialEstimateViewHolder(final View itemView) {
            super(itemView);
            estimateCv = (CardView)itemView.findViewById(R.id.material_estimate_cv);
            estimateCv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    MaterialEstimate materialEstimate = com.example.paulivanov.construx.MaterialEstimate.findById(MaterialEstimate.class, materialEstId);
                    boolean result = materialEstimate.delete();
                    view.refreshDrawableState();
                    Snackbar.make(view, "Material Estimate Deleted", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    return result;
                }
            });
            MaterialName = (TextView)itemView.findViewById(R.id.material_est_name);
            MaterialPrice = (TextView)itemView.findViewById(R.id.material_est_price);
            MaterialTotalPrice = (TextView)itemView.findViewById(R.id.material_est_total_price);
            MaterialTotalMeas = (TextView)itemView.findViewById(R.id.material_total_meas);
        }
    }
}
