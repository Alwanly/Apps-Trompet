package com.example.rpl.trompey;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FragmentUser extends Fragment implements View.OnClickListener {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    EditText mUserView;
    Button msignOut;
    private  View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        v =  lf.inflate(R.layout.fragment_user, container, false);
        mUserView = v.findViewById(R.id.edit_name);
        msignOut = v.findViewById(R.id.signOut);

        msignOut.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mUserView.setText(mFirebaseUser.getDisplayName());

        return v;
    }


    @Override
    public void onClick(View v) {
        mFirebaseAuth.signOut();
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        getActivity().finish();
        startActivity(intent);

    }


}