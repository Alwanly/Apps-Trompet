package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrasiActivity extends AppCompatActivity {
    EditText TextEmail,TextPassword;
    Button BtnRegister;
    Context mContext;
    ProgressDialog loading;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 101 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mContext = this;
        Intent data = getIntent();
        mAuth = FirebaseAuth.getInstance();
        iniComponents();
    }

    private void iniComponents() {
        TextPassword = findViewById(R.id.edittext_pass);
        TextEmail = findViewById(R.id.edittext_email);
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
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Authentication(user);
                       }else{
                            loading.dismiss();
                            Toast.makeText(RegistrasiActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void signUpGoogle(View view) {
        loading = ProgressDialog.show(this,null,"Wait...",true,false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("11", "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            loading.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();
                            Authentication(user);



                        } else {
                            loading.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w("error", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    public void Authentication(FirebaseUser user) {
        user = mAuth.getCurrentUser();
            
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users");

        Query query = db.equalTo(user.getUid());
        
        if (query == null){
            String email = user.getEmail();
            String uId = user.getUid();

            HashMap<Object, String> hashMap = new HashMap<>();

            hashMap.put("email",email);
            hashMap.put("uid",uId);
            hashMap.put("name","");
            hashMap.put("phone","");
            hashMap.put("address","");

            db.child(uId).setValue(hashMap);

            Toast.makeText(RegistrasiActivity.this, "Authentication Success", Toast.LENGTH_SHORT).show();

            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);
            finish();
        }else {
            Toast.makeText(this, "Sudah terdaftar", Toast.LENGTH_SHORT).show();
        }
    }
}
