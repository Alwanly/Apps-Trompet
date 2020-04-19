package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DetailsGroomingActivity extends AppCompatActivity {
    String paket,isi,harga;
    DatabaseReference myRef;
    FirebaseAuth Auth;
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

        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Paket Grooming");
    }
    public void Book(View view) {
        String id = myRef.push().getKey();
        String email = Auth.getCurrentUser().getEmail();

        HashMap<Object, String> datapaketgrooming = new HashMap<>();
        datapaketgrooming.put("id", id);
        datapaketgrooming.put("email", email);
        datapaketgrooming.put("paket", paket);
        datapaketgrooming.put("isi", isi);
        datapaketgrooming.put("harga", harga);

        myRef.child(id).setValue(datapaketgrooming);

        Toast.makeText(this, "Succes",Toast.LENGTH_SHORT).show();
        Intent book = new Intent(this,BookGroomingActivity.class);
        startActivity(book);

    }
}
