package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.List;

/**
 * Created by PaulIvanov on 10/27/2017.
 */

public class MaterialEstimate extends SugarRecord {
    @NotNull
    private Estimate estimate;
    @NotNull
    private Material material;
    @NotNull
    private int materialPrice;

    private List<Measurement> measurements;

    public MaterialEstimate() {}

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(int materialPrice) {
        this.materialPrice = materialPrice;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
