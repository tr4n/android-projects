package com.tr4n.listinstalledapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;

public class DetailInformation extends AppCompatActivity implements Serializable {
    private static final String TAG = "DetailInformation";
    TextView tvName, tvPackageName, tvSourceDir, tvInstallTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);
        Define();
        Initialization();

    }

    private void Define(){
        tvName = findViewById(R.id.tv_name);
        tvPackageName = findViewById(R.id.tv_package_name);
        tvSourceDir = findViewById(R.id.tv_source_dir);
        tvInstallTime = findViewById(R.id.tv_install_time);
    }

    private void Initialization(){

       AppInfo appInfo = (AppInfo) getIntent().getSerializableExtra("appinfo");


        tvName.setText(appInfo.name);
        tvPackageName.setText(appInfo.packageName);
        tvSourceDir.setText(appInfo.sourceDir);
        tvInstallTime.setText(appInfo.installDate);


    }


}
