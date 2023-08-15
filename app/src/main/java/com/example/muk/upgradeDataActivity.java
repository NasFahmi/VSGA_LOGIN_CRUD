package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class upgradeDataActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private ImageView backToList;
    private EditText ETnim;
    private EditText ETnama;
    private EditText ETAlamat;
    private EditText ETTanggalLahir;
    private EditText ETJenisKelamin;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_data);

        backToList = (ImageView) findViewById(R.id.backToDashboard);
        ETnim = (EditText) findViewById(R.id.eTNIM);
        ETnama = (EditText) findViewById(R.id.eTNama);
        ETTanggalLahir = (EditText) findViewById(R.id.eTTAnggalLahir);
        ETJenisKelamin = (EditText) findViewById(R.id.eTJenisKelamin);
        ETAlamat = (EditText) findViewById(R.id.eTAlamat);
        btnAddData = (Button) findViewById(R.id.btnAddData);

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

            Intent backToList = new Intent(upgradeDataActivity.this, listDataActivity.class);
            startActivity(backToList);
            finish();
        });

        btnAddData.setOnClickListener(view -> {
            String nim = ETnim.getText().toString();
            String nama = ETnama.getText().toString();
            String tanggalLahir = ETTanggalLahir.getText().toString();
            String jenisKelamin = ETJenisKelamin.getText().toString();
            String alamat = ETAlamat.getText().toString();

            db.updateData(idSelected,nim,nama,tanggalLahir,jenisKelamin,alamat);

            Intent backToList = new Intent(upgradeDataActivity.this, listDataActivity.class);
            startActivity(backToList);
            finish();
        });
    }
}