package com.example.rpl.trompey;

import android.graphics.drawable.Drawable;

public class Obat {
    private String nama, harga,desc;
    private String gambar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Obat() {
    }

    public Obat(String nama, String harga, String desc, String gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
        this.desc = desc;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
