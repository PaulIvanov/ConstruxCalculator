package com.example.paulivanov.construx;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMaterialActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddMaterialActivity.this, SettingActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        Button AddMaterialButton = (Button) findViewById(R.id.add_note_submit_button);
        AddMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText newMaterialName = (EditText) findViewById(R.id.material_name_edit);
                EditText materialType = (EditText) findViewById(R.id.measurement_edit);
                try{
                        Material myMat = new Material();
                        myMat.setMaterialName(newMaterialName.getText().toString());
                        myMat.setUnitOfMeasure(materialType.getText().toString());
                        myMat.save();
                        Snackbar.make(view, "Added new Material", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent intent = new Intent(view.getContext(), SettingActivity.class);
                        finish();
                        view.getContext().startActivity(intent);
                }
                catch(Exception ex)
                {
                    Snackbar.make(view, "Something went wrong." + ex.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
