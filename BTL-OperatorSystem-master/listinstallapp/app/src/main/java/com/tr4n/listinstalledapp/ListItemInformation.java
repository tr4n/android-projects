package com.tr4n.listinstalledapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListItemInformation extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    GridView gvListInstalledApps;
    List<ApplicationInfo> applicationInfoList;
    List<AppInfo> appInfoList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_information);

       Define();
       Initialization();
       setupUI();
    }

    private void Define(){
        gvListInstalledApps = findViewById(R.id.gv_list_installed_apps);
    }
    private void Initialization(){

        final PackageManager packageManager = getPackageManager();
       applicationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
       appInfoList = new ArrayList<>();


        for (ApplicationInfo applicationInfo : applicationInfoList) {
            String name = packageManager.getApplicationLabel(applicationInfo).toString();
            String packageName = applicationInfo.packageName;
            Drawable drawableIcon = applicationInfo.loadIcon(packageManager);
            String sourceDir = applicationInfo.sourceDir;
            Intent launchActivity = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);
            String installDate = "";

            try {
                long firstInstallTime = packageManager.getPackageInfo(applicationInfo.packageName, 0).firstInstallTime;
                installDate = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]").format(new Date(firstInstallTime));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }



            AppInfo appInfo = new AppInfo(name, packageName, drawableIcon, sourceDir, launchActivity, installDate);
            appInfoList.add(appInfo);

            Log.d(TAG, "Installed package :" + applicationInfo.packageName);
            Log.d(TAG, "Source dir : " + applicationInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + packageManager.getLaunchIntentForPackage(applicationInfo.packageName));
            Log.d(TAG, " Name : " + packageManager.getApplicationLabel(applicationInfo).toString());
            Log.d(TAG, "First Install time: " + installDate);
        }
        AppAdapter appAdapter = new AppAdapter(appInfoList);
        gvListInstalledApps.setAdapter(appAdapter);
    }

    ProgressBar progressBar;
    private void setupUI(){
        gvListInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListItemInformation.this, DetailInformation.class);
                AppInfo appInfo = appInfoList.get(position);
                intent.putExtra("appinfo", appInfo);


                startActivity(intent);
            }
        });

    }

}
