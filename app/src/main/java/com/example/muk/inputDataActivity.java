package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class inputDataActivity extends AppCompatActivity {
    private ImageView backToDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        backToDashboard = (ImageView) findViewById(R.id.backToDashboard);
        backToDashboard.setOnClickListener(view -> {
            Intent backToDashboard = new Intent(inputDataActivity.this,dashboardActivity.class);
            startActivity(backToDashboard);
            finish();
        });
    }
}