package com.example.mypc.weatherapp;

import java.util.List;

public class WeatherResponse {
    public String cod= "404";
    public String message;
    public List<WeatherJson> weather ;

    public class WeatherJson{
        String main;
        String description;
    }

}
