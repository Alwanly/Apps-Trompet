package com.example.rpl.trompey;


import android.content.Intent;

import android.os.Bundle;


import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class FragmentUser extends Fragment implements View.OnClickListener {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    DatabaseReference db;

    EditText mName,mEmail,mphone,maddress;
    Button msignOut,btnEdit;

    private  View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        v =  lf.inflate(R.layout.fragment_user, container, false);
        mName = v.findViewById(R.id.editText);
        mEmail = v.findViewById(R.id.editText2);
        mphone = v.findViewById(R.id.editText3);
        maddress = v.findViewById(R.id.editText4);

        btnEdit = v.findViewById(R.id.button2);
        msignOut = v.findViewById(R.id.button3);


        btnEdit.setOnClickListener(this);
        msignOut.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        db = FirebaseDatabase.getInstance().getReference("Users");

        Query query = db.orderByChild("email").equalTo(mFirebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    String name = ds.child("name").getValue().toString();
                    String email = ds.child("email").getValue().toString();
                    String phone = ds.child("phone").getValue().toString();
                    String address = ds.child("address").getValue().toString();

                    mName.setText(name);
                    mEmail.setText(email);
                    mphone.setText(phone);
                    maddress.setText(address);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.button3:
               signOut();
               break;
           case  R.id.button2:
               editProfile();
               break;
       }
    }

    private void editProfile() {



        HashMap<Object, String> hashMap = new HashMap<>();

        hashMap.put("email",mEmail.getText().toString());
        hashMap.put("uid", mFirebaseUser.getUid());
        hashMap.put("name",mName.getText().toString());
        hashMap.put("phone",mphone.getText().toString());
        hashMap.put("address",maddress.getText().toString());

        db.child(mFirebaseUser.getUid()).setValue(hashMap);

        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

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