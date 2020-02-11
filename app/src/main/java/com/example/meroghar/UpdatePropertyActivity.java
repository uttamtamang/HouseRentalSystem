package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpdatePropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_property);

        getSupportActionBar().setTitle("Update property");
    }
}
