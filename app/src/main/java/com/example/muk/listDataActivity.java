package com.example.muk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class listDataActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private ListView listItem;
    private ImageView backToDashboard;
    private ArrayList<String> namaMahasiswaList = new ArrayList<>();
    private ArrayList<String> idMahasiswaList = new ArrayList<>();
    private FloatingActionButton fabInputData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        backToDashboard = (ImageView) findViewById(R.id.backToDashboard);
        listItem = (ListView) findViewById(R.id.listDataNama);
        fabInputData = (FloatingActionButton) findViewById(R.id.fabInputData);

        //Intent
        backToDashboard.setOnClickListener(view -> {
            Intent backToDashboard = new Intent(listDataActivity.this,dashboardActivity.class);
            startActivity(backToDashboard);
            finish();
        });
        fabInputData.setOnClickListener(view -> {
            Intent toInputData = new Intent(listDataActivity.this, inputDataActivity.class);
            startActivity(toInputData);
            finish();
        });
        List<ModelDataMahasiswa> listDataMahasiswa= db.ListData();
        for (ModelDataMahasiswa data : listDataMahasiswa) {
            namaMahasiswaList.add(data.getNama());
            idMahasiswaList.add(data.getId());
        }


        //listView Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, namaMahasiswaList);
        listItem.setAdapter(adapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            // Dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(listDataActivity.this);
                builder.setTitle("Pilihan");
                String[] pilihan = {"Lihat Data","Update Data","Hapus Data"};
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent toDetailData = new Intent(listDataActivity.this,detailDataActivity.class);
                                String idSelected = listDataMahasiswa.get(position).getId();
                                toDetailData.putExtra("selectedId",idSelected);
//                                Log.e("id yang teseleksi",idSelected);
                                startActivity(toDetailData);
                                finish();
                                break;
                            case 1:
                                Intent toUpgradeData = new Intent(listDataActivity.this,upgradeDataActivity.class);
                                String idSelectedUpgrade = listDataMahasiswa.get(position).getId();
                                toUpgradeData.putExtra("selectedId",idSelectedUpgrade);
                                startActivity(toUpgradeData);
                                finish();
                                break;
                            case 2:
                                String idSelectedDelete = listDataMahasiswa.get(position).getId();
                                db.deleteData(idSelectedDelete);
                                refreshData();
                                Toast.makeText(listDataActivity.this,"Item Telah Terhapus Hapus Data",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void refreshData() {
        namaMahasiswaList.clear();
        idMahasiswaList.clear();

        List<ModelDataMahasiswa> listDataMahasiswa = db.ListData();
        for (ModelDataMahasiswa data : listDataMahasiswa) {
            namaMahasiswaList.add(data.getNama());
            idMahasiswaList.add(data.getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, namaMahasiswaList);
        listItem.setAdapter(adapter);
    }

}