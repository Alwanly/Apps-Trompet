package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ObatHewanBayar extends AppCompatActivity {
    TextView nama_barang, harga_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan_bayar);

        nama_barang = findViewById(R.id.nama_barang);
        harga_barang = findViewById(R.id.harga_barang);

        String nama = getIntent().getStringExtra("nama");
        String harga = getIntent().getStringExtra("harga");

        nama_barang.setText(nama);
        harga_barang.setText(harga);
    }

    public void Bayar(View view) {
        startActivity(new Intent(this, ObatHewanCheckOut.class));
    }
}
