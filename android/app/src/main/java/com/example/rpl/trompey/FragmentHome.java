package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class FragmentHome extends Fragment implements View.OnClickListener {
    LinearLayout menuDoctor,menuPetFood,menuGrooming,menuMedicine;
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        menuGrooming = v.findViewById(R.id.menu_grooming);
        menuDoctor = v.findViewById(R.id.menu_doctor);
        menuMedicine = v.findViewById(R.id.menu_medicine);
        menuPetFood = v.findViewById(R.id.menu_pet_food);

        menuPetFood.setOnClickListener(this);
        menuMedicine.setOnClickListener(this);
        menuDoctor.setOnClickListener(this);
        menuGrooming.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_doctor:
                Toast.makeText(getActivity(),"Doctor",Toast.LENGTH_SHORT).show();
                break;
                case R.id.menu_grooming:
                Toast.makeText(getActivity(),"Grooming",Toast.LENGTH_SHORT).show();
                break;
                case R.id.menu_medicine:
                Toast.makeText(getActivity(),"Medicice",Toast.LENGTH_SHORT).show();
                break;
                case R.id.menu_pet_food:
                    Intent obat = new Intent(getActivity(),ObatHewan.class);
                    getActivity().startActivity(obat);
                Toast.makeText(getActivity(),"Pet Food",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

