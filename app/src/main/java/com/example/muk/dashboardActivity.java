package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class dashboardActivity extends AppCompatActivity {
    private Button toListData;
    private Button toInputData;
    private Button toInformasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toListData = (Button) findViewById(R.id.btnLihatdata);
        toInputData = (Button) findViewById(R.id.btnInputData);
        toInformasi = (Button) findViewById(R.id.btnInformasi);

        toInformasi.setOnClickListener(view -> {
            Intent toInformasi = new Intent(dashboardActivity.this,informasiActivity.class);
            startActivity(toInformasi);
            finish();
        });
        toInputData.setOnClickListener(view -> {
            Intent toInputData = new Intent(dashboardActivity.this, inputDataActivity.class);
            startActivity(toInputData);
            finish();
        });
        toListData.setOnClickListener(view -> {
            Intent tolistData = new Intent(dashboardActivity.this, listDataActivity.class);
            startActivity(tolistData);
            finish();
        });

    }


}