package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    Button btnLogin;
    TextView mtvDaftar;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 101 ;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        mtvDaftar = findViewById(R.id.txt_daftar);
        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        mtvDaftar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signInButtonImpl).setOnClickListener(this);
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
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Authentication(user.getUid());
                        } else {
                            loading.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w("error", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

//        if (mAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(this,MainActivity.class));
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                loading = ProgressDialog.show(this,null,"Wait...",true,false);
                login();
                break;
            case R.id.signInButtonImpl:
                loading = ProgressDialog.show(this,null,"Wait...",true,false);
                signIn();
                break;
            case R.id.txt_daftar:
                Intent intent = new Intent(this,RegistrasiActivity.class);
                startActivity(intent);
                break;
                default:
                    return;
        }
    }

    private void login() {

        if (email.getText().toString().equals("admin@admin.com")){
            Intent admin = new Intent(this,MenuAdminActivity.class);
            startActivity(admin);
            finish();
            loading.dismiss();
        }else {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        loading.dismiss();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Authentication(user.getUid());
                    } else {
                        loading.dismiss();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void signIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void Authentication(String uid){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users");

        Query query = db.equalTo(uid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(login);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
