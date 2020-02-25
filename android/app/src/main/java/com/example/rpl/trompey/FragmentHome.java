package com.example.rpl.trompey;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentHome extends Fragment {
private View v;

public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    v = inflater.inflate(R.layout.fragment_home, container, false);
    return v;
}


    }

