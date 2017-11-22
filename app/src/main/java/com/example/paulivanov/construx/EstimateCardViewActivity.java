package com.example.paulivanov.construx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EstimateCardViewActivity extends AppCompatActivity {
    TextView EstimateName;
    TextView EstimatePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_card_view);
    }
}
