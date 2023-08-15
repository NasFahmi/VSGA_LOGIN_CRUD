package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class detailDataActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private ImageView backToList;
    private EditText ETnim;
    private EditText ETnama;
    private EditText ETAlamat;
    private EditText ETTanggalLahir;
    private EditText ETJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        backToList = (ImageView) findViewById(R.id.backToDashboard);
        ETnim = (EditText) findViewById(R.id.eTNIM);
        ETnama = (EditText) findViewById(R.id.eTNama);
        ETTanggalLahir = (EditText) findViewById(R.id.eTTAnggalLahir);
        ETJenisKelamin = (EditText) findViewById(R.id.eTJenisKelamin);
        ETAlamat = (EditText) findViewById(R.id.eTAlamat);

//        get id dari listview
        Intent intent = getIntent();
        String idSelected = intent.getStringExtra("selectedId");
        ModelDataMahasiswa data = db.getDetailDatabyId(idSelected);

        if (data != null) {
            // Set nilai TextView dengan data yang diambil
            ETnim.setText(data.getNim());
            ETnama.setText(data.getNama());
            ETTanggalLahir.setText(data.getTanggalLahir());
            ETJenisKelamin.setText(data.getJenisKelamin());
            ETAlamat.setText(data.getAlamat());
        } else {
            // Data tidak ditemukan atau terjadi kesalahan
            Toast.makeText(getBaseContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        backToList.setOnClickListener(view -> {
            Intent backToList = new Intent(detailDataActivity.this, listDataActivity.class);
            startActivity(backToList);
            finish();
        });

    }
}