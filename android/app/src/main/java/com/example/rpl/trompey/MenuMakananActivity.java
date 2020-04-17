package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;


public class MenuMakananActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ArrayList<RowItem> rowItems;
    private String[] member_names;
    private TypedArray profile_pics;
    private String[] status;
    private String[] contact_type;
    private RecyclerView mylistview;
    private CostumAdapter adapterlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan);
        setTitle("Menu Makanan");
        rowItems = new ArrayList<RowItem>();
        member_names = getResources().getStringArray(R.array.member_names);
        profile_pics = getResources().obtainTypedArray(R.array.profile_pics);
        status = getResources().getStringArray(R.array.status);
        contact_type = getResources().getStringArray(R.array.contact_type);

        for (int i = 0; i < member_names.length; i++) {
            RowItem item = new RowItem(member_names[i],
                    profile_pics.getResourceId(i, -1), status[i],
                    contact_type[i]);
            rowItems.add(item);
        }
        mylistview = findViewById(R.id.list);
        mylistview.setLayoutManager(new GridLayoutManager(this,2));
        adapterlist = new CostumAdapter(this, rowItems);
        mylistview.setAdapter(adapterlist);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String member_name = rowItems.get(position).getMember_name();
        Toast.makeText(getApplicationContext(), "" + member_name,
                Toast.LENGTH_SHORT).show();

    }

    public void beli(View view) {
        Intent beli1 = new Intent(MenuMakananActivity.this, PembayaranMakananActivity.class);
        startActivity(beli1);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}




