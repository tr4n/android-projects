package com.tr4n.listinstalledapp;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final long TEMPLE = 0x100000L;

    private Button btOpenList ;
    TextView tvMemoryPercent, tvAvailableSpace ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Define();
        Initialization();


        CountDownTimer countDownTimer = new CountDownTimer(1000000, 1000) {
            @Override
            public void onTick(long l) {
                setupUI();

            }

            @Override
            public void onFinish() {

            }
        }.start();



    }

    private void setupUI() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        final double availableMegs = memoryInfo.availMem/ TEMPLE;
        final double percentAvail = (double) memoryInfo.availMem / memoryInfo.totalMem * 100;

     //   Log.d(TAG, "setupUI: " + availableMegs + " || " + percentAvail);

        CountDownTimer countDownTimer = new CountDownTimer(100000, 2000) {
            @Override
            public void onTick(long l) {
                String string = String.format(" %02.2f", percentAvail);
                tvMemoryPercent.setText("Memory percent : " + string + " %");
              //  Log.d(TAG, "onTick: " + string );
                Log.d(TAG, "onTick: " + availableMegs + string);
            }

            @Override
            public void onFinish() {

            }
        }.start();



    }

    private void Define(){
        btOpenList  = findViewById(R.id.bt_open_list);
        tvMemoryPercent = findViewById(R.id.tv_memory_percent);
        tvAvailableSpace =  findViewById(R.id.tvAvailableSpace);

    }

    private void Initialization(){

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvaiable = 0;
        long MGAvailable = bytesAvaiable/1048576;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bytesAvaiable = statFs.getBlockSizeLong() * statFs.getBlockCountLong();
            MGAvailable = bytesAvaiable/1048576;

        }

        tvAvailableSpace.setText("Avaiable Space: " + MGAvailable + " MB");



        btOpenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ListItemInformation.class);
                startActivity(intent);
                
            }
        });

    }



}
