package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrasiActivity extends AppCompatActivity {
    EditText TextName, TextEmail,TextPassword;
    Button BtnRegister;
    Context mContext;
    BaseApiService mApiService;
    ProgressDialog loading;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        Intent data = getIntent();

        TextView email = findViewById(R.id.textView_judul);
        String nama = data.getStringExtra("NAMA");
        email.setText(nama);
        iniComponents();
    }

    private void iniComponents() {
        TextPassword = findViewById(R.id.edittext_pass);
        TextEmail = findViewById(R.id.edittext_email);
        TextName= findViewById(R.id.edittext_username);
        BtnRegister = findViewById(R.id.button_daftar);

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext,null,"Wait...",true,false);
                registerUser();
            }
        });
    }

    private void registerUser() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(TextEmail.getText().toString(),TextPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loading.dismiss();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(RegistrasiActivity.this,user.getEmail(),Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(RegistrasiActivity.this,MainActivity.class));

                    }
                });
    }
}
