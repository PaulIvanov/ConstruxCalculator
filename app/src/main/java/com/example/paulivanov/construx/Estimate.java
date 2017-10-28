package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

import java.util.List;

/**
 * Created by PaulIvanov on 10/27/2017.
 */

public class Estimate extends SugarRecord {
    @NotNull
    private Job job;

    @NotNull
    private String estimatesName;

    @NotNull
    private int totalPrice; // in USD cents fits up 21 million dollars

    public List<MaterialEstimate> materialEstimates;

    public Estimate() {}

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getEstimatesName() {
        return estimatesName;
    }

    public void setEstimatesName(String estimatesName) {
        this.estimatesName = estimatesName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
