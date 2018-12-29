package com.example.mypc.ramandstorage;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String arch = System.getProperty("os.arch");
//        Log.v("Scan Class", getDeviceName());
//        Toast.makeText(this, getDeviceName(), Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.tv_information);
        tv.setText("***** DEVICE Information *****" + "\n");
        tv.append("Model: " + Build.MODEL + "\n");
        tv.append("Board: " + Build.BOARD + "\n");
        tv.append("Brand: " + Build.BRAND + "\n");
        tv.append("Manufacturer: " + Build.MANUFACTURER + "\n");
        tv.append("Device: " + Build.DEVICE + "\n");
        tv.append("Product: " + Build.PRODUCT + "\n");
        tv.append("TAGS: " + Build.TAGS + "\n");
        tv.append("Serial: " + Build.SERIAL + "\n");
        tv.append("\n" + "***** Memory Info *****" + "\n");
        tv.append(getMemoryInfo() + "\n");

    }


    public String getMemoryInfo() {
        ProcessBuilder cmd;
        String result="";

        try {
            String[] args = {"/system/bin/cat", "/proc/meminfo"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}