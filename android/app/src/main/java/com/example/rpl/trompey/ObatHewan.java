package com.example.rpl.trompey;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObatHewan extends AppCompatActivity {

    private RecyclerView rvObatHewan;
    private ArrayList<Obat> obatlist;
    private ObatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan);

        setTitle("List Obat Hewan");

        rvObatHewan = findViewById(R.id.rv_obat_hewan);

        rvObatHewan.setLayoutManager(new GridLayoutManager(this,2));

        obatlist = new ArrayList<>();
        mAdapter = new ObatAdapter(this,obatlist);
        rvObatHewan.setAdapter(mAdapter);

        getDataObat();
    }

    private void getDataObat() {
        String[] nama = getResources().getStringArray(R.array.nama_obat);
        String[] harga = getResources().getStringArray(R.array.harga_obat);
        TypedArray gambar = getResources().obtainTypedArray(R.array.gambar_obat);


        obatlist.clear();

        for (int i = 0; i < nama.length; i++) {
            obatlist.add(new Obat(nama[i], harga[i],
                    gambar.getResourceId(i, 0)));
        }
        gambar.recycle();
        mAdapter.notifyDataSetChanged();
    }
}
