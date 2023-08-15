package com.example.muk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class listDataActivity extends AppCompatActivity {
    private ListView listItem;
    private ImageView backToDashboard;
    private String[] listNama ={
            "Nama 1","Nama 2"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        backToDashboard = (ImageView) findViewById(R.id.backToDashboard);
        listItem = (ListView) findViewById(R.id.listDataNama);

        //Intent
        backToDashboard.setOnClickListener(view -> {
            Intent backToDashboard = new Intent(listDataActivity.this,dashboardActivity.class);
            startActivity(backToDashboard);
            finish();
        });

        //listView Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, listNama);
        listItem.setAdapter(adapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(listDataActivity.this);
                builder.setTitle("Pilihan");
                String[] pilihan = {"Lihat Data","Update Data","Hapus Data"};
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Toast.makeText(listDataActivity.this,"Anda memilih Lihat Data",Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(listDataActivity.this,"Anda memilih Upgrade Data",Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(listDataActivity.this,"Anda memilih Hapus Data",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}