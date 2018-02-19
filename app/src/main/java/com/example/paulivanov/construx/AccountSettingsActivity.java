package com.example.paulivanov.construx;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.Set;

public class AccountSettingsActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AccountSettingsActivity.this, SettingActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        Button changePasswordButton = (Button) findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currentUser = LoginActivity.CURRENT_USER;

                EditText oldPasswordEdit = (EditText) findViewById(R.id.old_password_edit);
                EditText newPasswordEdit = (EditText) findViewById(R.id.newPasswordEdit);

                try{
                    if(currentUser.getPassword().equals(oldPasswordEdit.getText().toString()))
                    {
                        currentUser.setPassword(newPasswordEdit.getText().toString());
                        currentUser.save();
                        Snackbar.make(view, "Password change successful", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent intent = new Intent(view.getContext(), SettingActivity.class);
                        finish();
                        view.getContext().startActivity(intent);

                    }
                    else
                    {
                        Snackbar.make(view, "Password Change failed. Incorrect old password.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
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
