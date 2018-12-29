package com.example.m1k3y.messenger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btSignIn, btSignUp;
    private static final String TAG = "MainActivity";

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_id);
        etPassword = findViewById(R.id.et_book);
        btSignIn = findViewById(R.id.bt_read);
        btSignUp = findViewById(R.id.bt_add);

        firebaseAuth = FirebaseAuth.getInstance();

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }

        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {

    }

    private void signUp() {
        //show dialog
        firebaseAuth.createUserWithEmailAndPassword(
                etEmail.getText().toString(),
                etPassword.getText().toString()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //hide dialog
                if(task.isSuccessful()){
                    Log.d(TAG, "onComplete: " + "sign up successfully");
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "onComplete: " + " send email successfully");
                            }else{
                                Log.d(TAG, "onComplete: " + " send email veri failed");
                            }

                        }
                    });

                    // check user verified


                }else{
                    Log.d(TAG, "onComplete: " + task.getException().getMessage());
                }
            }
        });
    }
}
