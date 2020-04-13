package com.example.rpl.trompey;

import android.graphics.drawable.Drawable;

public class Obat {
    private String nama, harga;
    private int gambar;

    public Obat(String nama, String harga, int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
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
