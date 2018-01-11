package com.example.paulivanov.construx;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditEstimateActivity extends AppCompatActivity {
    List<MaterialEstimate> materialEstimates;
    private RecyclerView materialEstimateRv;
    Estimate estimate;
    Job job;
    long jobId;
    long estId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jobId = this.getIntent().getLongExtra("job_id", 0);
        job = Job.findById(Job.class, jobId);

        estId = this.getIntent().getLongExtra("estimate_id", 0);
        estimate = Estimate.findById(Estimate.class, estId);
        estimate.materialEstimates = MaterialEstimate.find(MaterialEstimate.class, "estimate = ?", estimate.toString());

        setContentView(R.layout.activity_edit_estimate);

        //set up dropdown material list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.materials_list, android.R.layout.simple_spinner_dropdown_item);
        Spinner materialList = (Spinner) findViewById(R.id.material_spinner);
        materialList.setAdapter(adapter);


        Button addMeasButton = (Button) findViewById(R.id.addMeasButton);

        addMeasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText measHeight = (EditText) findViewById(R.id.meas_height);
                EditText measWidth = (EditText) findViewById(R.id.meas_width);
                Spinner spinnerText = (Spinner) findViewById(R.id.material_spinner);
                try{
                    int length = Integer.parseInt(measHeight.getText().toString());
                    int width = Integer.parseInt(measWidth.getText().toString());

                    MaterialEstimate materialEstimate = GetMaterialEstimate(spinnerText.getSelectedItem().toString());
                    Measurement newMeas = materialEstimate.addMeasurement(length, width);
                    Snackbar.make(view, "measurement added: ID# " + newMeas.getId().toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch(Exception ex)
                {
                    Snackbar.make(view, "measurement Failed: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        materialEstimateRv = (RecyclerView)findViewById(R.id.materialEstRv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        materialEstimateRv.setLayoutManager(llm);
        materialEstimateRv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        try
        {
            List<MaterialEstimate> newMaEstimates = MaterialEstimate.find(MaterialEstimate.class, "1=1");
            materialEstimates = new ArrayList<>();
            for(MaterialEstimate est : newMaEstimates)
            {
                if(est.getEstimate().getId() == estId) {
                    materialEstimates.add(est);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    private void initializeAdapter(){
        MaterialEstimateRvAdapter adapter = new MaterialEstimateRvAdapter(materialEstimates);
        materialEstimateRv.setAdapter(adapter);
    }

    protected MaterialEstimate GetMaterialEstimate(String material){
            try {
                for(MaterialEstimate matest : this.estimate.materialEstimates)
                {
                    if(matest.getMaterial().getMaterialName().equals(material)){
                        return matest;
                    }
                }
                throw new Exception("Didnt find it oh well");
            }
            catch(Exception e)
            {
                MaterialEstimate newMat = new MaterialEstimate();
                newMat.setEstimate(this.estimate);
                List<Material> mats = Material.find(Material.class, "material_name = ?", material);
                newMat.setMaterial(mats.get(0));
                newMat.setMaterialPrice(1);
                newMat.save();
                if(this.estimate.materialEstimates == null)
                {
                    this.estimate.materialEstimates = new ArrayList<>();
                }
                this.estimate.materialEstimates.add(newMat);
                this.estimate.save();
                return newMat;
            }
    }


}
