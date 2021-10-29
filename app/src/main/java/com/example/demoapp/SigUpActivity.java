package com.example.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtSigupEmail,edtSiguppass;
    private ImageView imgBack;
    private String sigupEmail;
    private String sigupPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);
        mAuth = FirebaseAuth.getInstance();
        edtSigupEmail = findViewById(R.id.edtSigUpEmail);
        edtSiguppass=findViewById(R.id.edtSigUpPassword);
        imgBack = findViewById(R.id.imgBack);
        sigupEmail = edtSigupEmail.getText().toString();
        sigupPassword = edtSiguppass.getText().toString();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigUpActivity.this,LogInActivity.class));
                finish();
            }
        });
        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BaseActivity baseActivity1 = new BaseActivity();
                baseActivity1.sigUp(sigupEmail, sigupPassword,edtSigupEmail,edtSiguppass, new BaseActivity.SigninCallBack(){
                    @Override
                    public void onSuccess() {
                        Log.e("errorLogInActivity","sigup ok ");
                        Toast.makeText(SigUpActivity.this,"dang ky thanh cong",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SigUpActivity.this,LogInActivity.class));
                    }
                    @Override
                    public void onFailure() {
                        Log.e("errorLogInActivity","sigup fail");
                        Toast.makeText(SigUpActivity.this,"loi dang ky",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }




}