package com.example.demoapp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class BaseActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseDatabase firebaseDatabase;
    public GoogleSignInClient mGoogleSignInClient;
    public DatabaseReference databaseReference;

    public static final int RC_SIGN_IN = 123;

    public interface SigninCallBack {
        void onSuccess();
        void onFailure();
    }
    public void SwichOnOff(Switch aSwitch){
        firebaseDatabase= FirebaseDatabase.getInstance();
        if (aSwitch.isChecked()==true){
            databaseReference = firebaseDatabase.getReference("congtac");
            databaseReference.setValue("1");


        }else {
            databaseReference = firebaseDatabase.getReference("congtac");
            databaseReference.setValue("0");



        }
    }

    public void sigIn(String email, String password,EditText edtemail,EditText edtpass, SigninCallBack signinCallBack) {
        mAuth= FirebaseAuth.getInstance();
        if (edtemail.getText().toString().isEmpty()|| edtpass.getText().toString().isEmpty()){
            Log.e("error_BaseActivity","email,password chua dung");
            return;
        }else {
            mAuth.signInWithEmailAndPassword(edtemail.getText().toString(), edtpass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e("error_BaseActivity","ok");
                                signinCallBack.onSuccess();
                            } else {
                                signinCallBack.onFailure();
                                Log.e("error_BaseActivity","fail");
                            }
                        }
                    });
        }
    }
    public void sigUp(String email, String password,EditText edtemail,EditText edtpass, SigninCallBack signinCallBack) {
        mAuth= FirebaseAuth.getInstance();
        if (edtemail.getText().toString().isEmpty()|| edtpass.getText().toString().isEmpty()){
            Log.e("error_BaseActivity","email,password chua dung");
            return;
        }else {
            mAuth.createUserWithEmailAndPassword(edtemail.getText().toString(), edtpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.e("error_BaseActivity","sigup success");
                        signinCallBack.onSuccess();
                    } else {
                        signinCallBack.onFailure();
                        Log.e("error_BaseActivity","sigup failure");
                    }
                }
            });
        }
    }



//    public void firebaseAuthWithGoogle(GoogleSignInAccount acct){
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
//        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Log.e("error_BaseActivity","ok");
//                }
//                else {
//                    Log.e("error_BaseActivity","fail");
//                }
//            }
//        });
//    }


//    public void createRequest(){
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    }
//    public void signInGoogle() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//@Override
//public void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//    if (requestCode == RC_SIGN_IN) {
//        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//        try {
//            // Google Sign In was successful, authenticate with Firebase
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            GoogleSignInAccount account = task.getResult(ApiException.class);
//            firebaseAuthWithGoogle(account);
//        } catch (ApiException e) {
//            // Google Sign In failed, update UI appropriately
////                Toast.makeText(MainActivity.this,"fail"+e.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }
//}


}








