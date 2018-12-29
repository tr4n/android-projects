package com.example.mypc.cpu_z;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListInstalledAppsActivity extends AppCompatActivity implements Serializable{
    private static final String TAG = "MainActivity";

    GridView gvInstalledApps;
    List<ApplicationInfo> applicationInfoList;
    List<AppInfo> appInfoList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_installed_apps);

        Define();
        Initialization();
        setupUI();
    }

    private void Define(){
        gvInstalledApps = findViewById(R.id.gv_installed_apps);
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
        InstalledAppAdapter installedAppAdapter = new InstalledAppAdapter(appInfoList);
        gvInstalledApps.setAdapter(installedAppAdapter);
    }


    private void setupUI(){
        gvInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListInstalledAppsActivity.this, DetailInformation.class);
                AppInfo informationOfApp = appInfoList.get(position);

                intent.putExtra("position",position);
                /*
                String name = appInfo.name;
                String packageName = appInfo.packageName;
                Drawable drawableIcon = appInfo.drawableIcon;
                String sourceDir = appInfo.sourceDir;
                Intent launchActivity = appInfo.launchActivity;
                String installDate = appInfo.installDate;

                intent.putExtra("name", name);
                intent.putExtra("packageName", packageName);
                intent.putExtra("sourceDir", sourceDir);
                intent.putExtra("launchActivity",launchActivity);
                intent.putExtra("installDate", installDate);
                */
                startActivity(intent);
            }
        });

    }
}
