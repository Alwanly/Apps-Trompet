package com.example.rpl.trompey;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObatHewan extends AppCompatActivity {

    private RecyclerView rvObatHewan;
    private ArrayList<Obat> obat;
    private ObatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan);

        setTitle("List Obat Hewan");

        rvObatHewan = findViewById(R.id.rv_obat_hewan);

        rvObatHewan.setLayoutManager(new LinearLayoutManager(this));

        obat = new ArrayList<>();
        mAdapter = new ObatAdapter(this, obat);

        rvObatHewan.setAdapter(mAdapter);

        getDataObat();
    }

    private void getDataObat() {
        String[] nama = getResources().getStringArray(R.array.nama_obat);
        String[] harga = getResources().getStringArray(R.array.harga_obat);
        TypedArray gambar = getResources().obtainTypedArray(R.array.gambar_obat);

        obat.clear();

        for (int i = 0; i < nama.length; i++) {
            obat.add(new Obat(nama[i], harga[i],
                    gambar.getResourceId(i, 0)));
        }

        gambar.recycle();
        mAdapter.notifyDataSetChanged();
    }
}
