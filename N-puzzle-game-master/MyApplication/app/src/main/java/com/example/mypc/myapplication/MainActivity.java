package com.example.mypc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* 1. Create a retrofit */
        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://qkserver1.herokuapp.com/api/")
                .build();

        /*
         *  2. Create Object
         */

        // 3. Create a interface Request

        // 4. Call  function

        RegisterService registerService = retrofit.create(RegisterService.class);
        registerService.register(new Register().new RegisterRequest(
                "HelloWorld",
                "123456",
                "stu"
        )).enqueue(new Callback<Register.RegisterResponse>() {
            @Override
            public void onResponse(Call<Register.RegisterResponse> call, Response<Register.RegisterResponse> response) {
                Log.d(TAG, "onResponse: (Rerister)" + response.body().message);
            }

            @Override
            public void onFailure(Call<Register.RegisterResponse> call, Throwable t) {
                // When no Internet
                Toast.makeText(MainActivity.this, "No Internet !" , Toast.LENGTH_SHORT).show();
            }
        });



        LoginService loginService = retrofit.create(LoginService.class);
        loginService.login(new Login(). new LoginRequest(
                "HelloWorld",
                "123456"
        )).enqueue(new Callback<Login.LoginResponse>() {
            @Override
            public void onResponse(Call<Login.LoginResponse> call, Response<Login.LoginResponse> response) {
                Log.d(TAG, "onResponse: (Login) " + response.body().message);
            }

            @Override
            public void onFailure(Call<Login.LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Internet !" , Toast.LENGTH_SHORT).show();
            }
        });


    }
}
