package com.example.rpl.trompey;


import android.content.Intent;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
        ImageView profil = v.findViewById(R.id.imageView);

        msignOut.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserView.setText(mFirebaseUser.getEmail());

        if (mFirebaseUser.getPhotoUrl() != null ){
            Glide.with(getActivity()).load(mFirebaseUser.getPhotoUrl()).override(300).into(profil);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.signOut:
               signOut();
               break;
       }
    }

    private void signOut() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getActivity().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mGoogleSignInClient.signOut();
        mFirebaseAuth.getInstance().signOut();
        Intent login = new Intent(getActivity(),LoginActivity.class);
        startActivity(login);
        Toast.makeText(getActivity(),"Logout",Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}