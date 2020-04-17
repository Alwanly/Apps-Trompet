package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListAdminActivity extends AppCompatActivity {

    private RecyclerView rvAdmin;
    private ArrayList<GroomingAdmin> adminArrayList;
    private AdminAdapter adminAdapter;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin);


        rvAdmin = findViewById(R.id.list_admin);
        int grid = getResources().getInteger(R.integer.gird);
        rvAdmin.setLayoutManager(new GridLayoutManager(this,grid));

        adminArrayList = new ArrayList<>();

        adminAdapter = new AdminAdapter(adminArrayList,this);

        rvAdmin.setAdapter(adminAdapter);

        getDataGrooming();
    }

    private void getDataGrooming() {

        db = FirebaseDatabase.getInstance().getReference("Users");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminArrayList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    adminArrayList.add(new GroomingAdmin(ds.child("name").getValue().toString(),"Waiting",R.drawable.logo_dokter));
                }
                adminAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
