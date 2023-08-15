package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class informasiActivity extends AppCompatActivity {
    private ImageView btnBackToDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        btnBackToDashboard = (ImageView) findViewById(R.id.backToDashboard);
        btnBackToDashboard.setOnClickListener(view -> {
            Intent backToDashboard = new Intent(informasiActivity.this, dashboardActivity.class);
            startActivity(backToDashboard);
            finish();
        });
    }
}