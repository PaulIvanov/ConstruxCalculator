package com.example.paulivanov.construx;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;

/**
 * Created by PaulIvanov on 10/27/2017.
 */

public class Measurement extends SugarRecord{

    @NotNull
    private MaterialEstimate materialEstimate;

    @NotNull
    private int length;

    @NotNull
    private int width;

    public Measurement(){}

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
