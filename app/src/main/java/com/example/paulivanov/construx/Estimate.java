package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

import java.util.Date;
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
    private long totalPrice; // in USD cents fits up 21 million dollars

    private Date creationDate;

    public List<MaterialEstimate> materialEstimates;

    public Estimate() {
        creationDate = new Date();
    }

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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date newDate) { this.creationDate = newDate; }

    public long calculateEstimateTotal()
    {
        List<MaterialEstimate> materialEstimates = MaterialEstimate.find(MaterialEstimate.class,
                "1=1");

        for(MaterialEstimate matEst : materialEstimates)
        {
            this.totalPrice += matEst.CalculateTotalPrice();
        }
        this.save();
        return this.totalPrice;
    }
}
