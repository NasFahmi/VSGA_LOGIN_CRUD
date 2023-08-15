package com.example.muk;

public class ModelDataMahasiswa {
    private String id;
    private String nim;
    private String nama;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;

    public ModelDataMahasiswa(String id, String nim, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getId() {
        return id;
    }
}
