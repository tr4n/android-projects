package com.example.mypc.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<Login.LoginResponse> login(@Body Login.LoginRequest loginRequest);
}
