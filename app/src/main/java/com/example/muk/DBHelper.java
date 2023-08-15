package com.example.muk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static  final String DATABASE_NAME = "db_DataMahasiswa.db";

    public static final String namaTabelData = "dataMahasiswa";
    public static final String Column_idAkun = "id";
    public static final String Column_nim = "nim";
    public static final String Column_nama = "nama";
    public static final String Column_tanggalLahir = "tanggal_lahir";
    public static final String Column_jenisKelamin = "jenis_kelamin";
    public static final String Column_alamat = "alamat";

    public static final String namaTabelUser = "user";
    public static final String Column_idUser = "id";
    public static final String Column_email = "email";
    public static final String Column_password = "password";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String queryCreateTabelAkun = "CREATE TABLE " + namaTabelData + " ( " +
                Column_idAkun + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_nim + " TEXT, " +
                Column_nama + " TEXT, " +
                Column_tanggalLahir + " TEXT, " +
                Column_jenisKelamin + " TEXT, " +
                Column_alamat + " TEXT " +
                ")";

        final String queryCreateTabelUser = "CREATE TABLE " + namaTabelUser + " ( " +
                Column_idUser + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_email + " TEXT, " +
                Column_password + " TEXT " +
                ")";
        sqLiteDatabase.execSQL(queryCreateTabelAkun);
        sqLiteDatabase.execSQL(queryCreateTabelUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + namaTabelData);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + namaTabelUser);
        onCreate(sqLiteDatabase);
    }

    public void register(String email, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "INSERT INTO " + namaTabelUser + " (" + Column_email + ", " + Column_password + ") " +
                "VALUES ('" + email + "', '" + password + "')";

        Log.e("berhasil Register Akun", "" + query);
        database.execSQL(query);
        database.close();
    }


    // login
    public boolean login(String email, String password){
        boolean result = false;
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + namaTabelUser + " WHERE " + Column_email + " = '" + email + "' AND " + Column_password + " = '" + password + "'";
        Log.e("akun terdeteksi", "" + query);
        Cursor cursor = database.rawQuery(query, null);
        Log.e("jumlah akun ", cursor.getCount() + "");

        // Jika jumlah akun != 0 maka ada akun tersebut, lalu return true
        if(cursor.getCount() != 0){
            // Berhasil login
            result = true;
        }

        cursor.close();
        database.close();
        return result;
    }
    public void AddData(String nim,String nama, String tanggalLahir, String jenisKelamin,String alamat){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "INSERT INTO " + namaTabelData + " (" + Column_nim + ", " + Column_nama +", " + Column_tanggalLahir + ", " + Column_jenisKelamin + ", " + Column_alamat +") " +
                "VALUES ('" + nim + "', '" + nama +"', '" + tanggalLahir +"', '" + jenisKelamin + "', '" + alamat +"')";
        Log.e("berhasil input data", "" + query);
        database.execSQL(query);
        database.close();
    }

    public List<ModelDataMahasiswa> ListData(){
        SQLiteDatabase database = this.getWritableDatabase();
        List<ModelDataMahasiswa> datalist = new ArrayList<>();
        String query = "SELECT * FROM "+namaTabelData;
        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(Column_idAkun);
            int nimColumnIndex = cursor.getColumnIndex(Column_nim);
            int namaColumnIndex = cursor.getColumnIndex(Column_nama);
            int tanggalLahirColumnIndex = cursor.getColumnIndex(Column_tanggalLahir);
            int jenisKelaminColumnIndex = cursor.getColumnIndex(Column_jenisKelamin);
            int alamatColumnIndex = cursor.getColumnIndex(Column_alamat);

            do {
                String id = cursor.getString(idColumnIndex);
                String nim = cursor.getString(nimColumnIndex);
                String nama = cursor.getString(namaColumnIndex);
                String tanggalLahir = cursor.getString(tanggalLahirColumnIndex);
                String jenisKelamin = cursor.getString(jenisKelaminColumnIndex);
                String alamat = cursor.getString(alamatColumnIndex);

                ModelDataMahasiswa data = new ModelDataMahasiswa(id,nim, nama, tanggalLahir, jenisKelamin, alamat);
                datalist.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return datalist;

    }
    public ModelDataMahasiswa getDetailDatabyId(String id){
        SQLiteDatabase database = this.getReadableDatabase();
        ModelDataMahasiswa data = null;

        String query = "SELECT * FROM " + namaTabelData + " WHERE " + Column_idAkun + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{id});

        if (cursor.moveToFirst()) {
            int nimColumnIndex = cursor.getColumnIndex(Column_nim);
            int namaColumnIndex = cursor.getColumnIndex(Column_nama);
            int tanggalLahirColumnIndex = cursor.getColumnIndex(Column_tanggalLahir);
            int jenisKelaminColumnIndex = cursor.getColumnIndex(Column_jenisKelamin);
            int alamatColumnIndex = cursor.getColumnIndex(Column_alamat);

            String nim = cursor.getString(nimColumnIndex);
            String nama = cursor.getString(namaColumnIndex);
            String tanggalLahir = cursor.getString(tanggalLahirColumnIndex);
            String jenisKelamin = cursor.getString(jenisKelaminColumnIndex);
            String alamat = cursor.getString(alamatColumnIndex);

            data = new ModelDataMahasiswa(id, nim, nama, tanggalLahir, jenisKelamin, alamat);
        }

        cursor.close();
        database.close();
        return data;
    }
    public void updateData(String id, String nim, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "UPDATE " + namaTabelData + " SET " + Column_nim + " = ?, " +
                Column_nama + " = ?, " + Column_tanggalLahir + " = ?, " +
                Column_jenisKelamin + " = ?, " + Column_alamat + " = ? " +
                "WHERE " + Column_idAkun + " = ?";

        database.execSQL(query, new String[]{nim, nama, tanggalLahir, jenisKelamin, alamat, id});
        database.close();
    }

    public void deleteData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(namaTabelData, Column_idAkun + " = ?", new String[]{id});
        database.close();
    }




}
