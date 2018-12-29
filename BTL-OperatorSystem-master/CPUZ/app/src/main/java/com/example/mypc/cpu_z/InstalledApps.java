package com.example.mypc.cpu_z;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstalledApps extends AppCompatActivity {
    private static final String TAG = "InstalledApps";

    public static List<AppInfo> installAppList;
    public PackageManager packageManager = getPackageManager();


    public  List<AppInfo> getInstallAppList(){

        List <ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        installAppList = new ArrayList<>();


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
            installAppList.add(appInfo);

            Log.d(TAG, "Installed package :" + applicationInfo.packageName);
            Log.d(TAG, "Source dir : " + applicationInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + packageManager.getLaunchIntentForPackage(applicationInfo.packageName));
            Log.d(TAG, " Name : " + packageManager.getApplicationLabel(applicationInfo).toString());
            Log.d(TAG, "First Install time: " + installDate);
        }

        return installAppList;
    }


}
