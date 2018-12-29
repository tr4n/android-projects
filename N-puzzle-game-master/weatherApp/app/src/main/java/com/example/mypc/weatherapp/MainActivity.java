package com.example.mypc.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText etGetCity;
    Button btGetWeather;
    TextView tvShowWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Definition();
        Initialization();
        setupUI();
    }

    private void setupUI() {

    }

    private void Initialization() {
        final WeatherService weatherService = RetrofitInstance.getRetrofitInstance().create(WeatherService.class);
        btGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherService.getWeather(etGetCity.getText().toString(), "906a2b3002477b221b9050c47f167fee")
                        .enqueue(new Callback<WeatherResponse>() {
                            @Override
                            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                                String information = "";
                                if (response.body() == null) {
                                   tvShowWeather.setText("city not found");
                                }else {
                                    for (WeatherResponse.WeatherJson weatherJson : response.body().weather) {
                                        information += (weatherJson.main + " | " + weatherJson.description + "\n");                                   }

                                    tvShowWeather.setText(information);
                                }


                            }

                            @Override
                            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                                // tvShowWeather.setText(etGetCity.getText());
                                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void Definition() {

        tvShowWeather = findViewById(R.id.tv_show_weather);
        btGetWeather = findViewById(R.id.bt_get_weather);
        etGetCity = findViewById(R.id.et_get_city);

    }
}
