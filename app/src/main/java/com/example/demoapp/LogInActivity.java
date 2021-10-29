package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private String checkEmail,checkPassWord;
    private EditText edtMail,edtPassWord;
    private ImageView imgSiginGoogle;
    public GoogleSignInClient mGoogleSignInClient=null;
    private FirebaseAuth mAuth;
    public static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
//
//        BaseActivity baseActivity = new BaseActivity();
//        baseActivity.createRequest();
////

        mAuth= FirebaseAuth.getInstance();
        edtMail = findViewById(R.id.edtLogInEmail);
        edtPassWord = findViewById(R.id.edtLogInPassword);
        checkEmail = edtMail.getText().toString();
        checkPassWord = edtPassWord.getText().toString();

//        findViewById(R.id.imgGoogle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//
//            }
//        });
//        findViewById(R.id.imgSigInGoogle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseActivity baseActivity1 = new BaseActivity();
//                baseActivity1.signInGoogle();
//            }
//        });
        findViewById(R.id.btnSigUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,SigUpActivity.class));
            }
        });
        findViewById(R.id.imgbtnLogin).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                BaseActivity baseActivitySigIn = new BaseActivity();
                        baseActivitySigIn.sigIn(checkEmail, checkPassWord,edtMail,edtPassWord, new BaseActivity.SigninCallBack(){
                            @Override
                            public void onSuccess() {
                                Log.e("errorLogInActivity","ok ");
                                Toast.makeText(LogInActivity.this,"dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LogInActivity.this,MainActivity.class));
                            }
                            @Override
                            public void onFailure() {
                                Log.e("errorLogInActivity","fail");
                                Toast.makeText(LogInActivity.this,"loi dang nhap",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}