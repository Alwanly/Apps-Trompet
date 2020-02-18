package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

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
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        mApiService.registerRequest(
                TextEmail.getText().toString(),
                TextPassword.getText().toString(),
                TextName.getText().toString().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.i("debug","onResponse:Successfully");
                    loading.dismiss();
                    try {
                        JSONObject jsonResults = new JSONObject(response.body().string());
                        if (jsonResults.getString("status").equals("success")){
                            Toast.makeText(mContext,"Success Registration",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext,MainActivity.class));
                        }else{
                            String error_message = jsonResults.getString("errors");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.i("Debug","onResponse:Decline");
                    loading.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("debug","onFailure:Error->"+t.getMessage());
                Toast.makeText(mContext,"Can't Connection Internet", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
