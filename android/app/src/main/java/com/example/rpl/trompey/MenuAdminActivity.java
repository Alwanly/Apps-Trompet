package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuAdminActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnGrooming,btnDocter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        btnGrooming = findViewById(R.id.menu_grooming);
        btnDocter =findViewById(R.id.menu_doctor);
        btnDocter.setOnClickListener(this);
        btnGrooming.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_grooming:
                Intent list = new Intent(this,ListAdminActivity.class);
                startActivity(list);
                break;
            case R.id.menu_doctor:
                Toast.makeText(this, "Dokter", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
