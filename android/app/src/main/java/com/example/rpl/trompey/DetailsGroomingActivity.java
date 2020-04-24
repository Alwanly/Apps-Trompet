package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsGroomingActivity extends AppCompatActivity {
    String paket,isi,harga;

    private String context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_grooming);

        Intent intent = getIntent();

        TextView tvPaket = findViewById(R.id.tv_paket);
        TextView tvIsi = findViewById(R.id.tv_isi);
        TextView tvHarga = findViewById(R.id.tv_harga);

        paket = intent.getStringExtra("PAKET");
        isi = intent.getStringExtra("ISI");
        harga = intent.getStringExtra("HARGA");

        tvPaket.setText(paket);
        tvIsi.setText(isi);
        tvHarga.setText(harga);
    }
    public void Book(View view) {




        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        Intent list = new Intent(this,BookGroomingActivity.class);
        startActivity(list);
    }
}
