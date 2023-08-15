package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class detailDataActivity extends AppCompatActivity {
    private ImageView backToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        backToList = (ImageView) findViewById(R.id.backToDashboard);
        backToList.setOnClickListener(view -> {
            Intent backToList = new Intent(detailDataActivity.this, listDataActivity.class);
            startActivity(backToList);
            finish();
        });
    }
}