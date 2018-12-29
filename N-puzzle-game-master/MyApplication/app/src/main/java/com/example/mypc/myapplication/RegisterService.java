package com.example.mypc.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("register")
    Call<Register.RegisterResponse> register(@Body Register.RegisterRequest registerRequest);


}
