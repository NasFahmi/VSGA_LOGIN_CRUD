package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class inputDataActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private ImageView backToDashboard;
    private EditText ETnim;
    private EditText ETnama;
    private EditText ETAlamat;
    private EditText ETTanggalLahir;
    private EditText ETJenisKelamin;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        backToDashboard = (ImageView) findViewById(R.id.backToDashboard);
        ETnim = (EditText) findViewById(R.id.eTNIM);
        ETnama = (EditText) findViewById(R.id.eTNama);
        ETTanggalLahir = (EditText) findViewById(R.id.eTTAnggalLahir);
        ETJenisKelamin = (EditText) findViewById(R.id.eTJenisKelamin);
        ETAlamat = (EditText) findViewById(R.id.eTAlamat);
        btnAddData = (Button) findViewById(R.id.btnAddData);

        backToDashboard.setOnClickListener(view -> {
            Intent backToDashboard = new Intent(inputDataActivity.this,dashboardActivity.class);
            startActivity(backToDashboard);
            finish();
        });
        btnAddData.setOnClickListener(view -> {
            String nim = ETnim.getText().toString();
            String nama =ETnama.getText().toString();
            String tanggalLahir = ETTanggalLahir.getText().toString();
            String jenisKelamin = ETJenisKelamin.getText().toString();
            String alamat =ETAlamat.getText().toString();
            db.AddData(nim,nama,tanggalLahir,jenisKelamin,alamat);
            Intent backToDashboard = new Intent(inputDataActivity.this,dashboardActivity.class);
            startActivity(backToDashboard);
            finish();

        });

    }
}