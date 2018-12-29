package com.example.mypc.cpu_z;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailInformation extends AppCompatActivity implements Serializable {
    private static final String TAG = "DetailInformation";
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_package)
    TextView tvPackage;
    @BindView(R.id.tv_source_dir)
    TextView tvSourceDir;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.bt_open_application)
    Button btOpenApplication;
    @BindView(R.id.tv_installed_date)
    TextView tvInstalledDate;

    private int position = 0;
    private AppInfo detailInformationApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);
        ButterKnife.bind(this);

        Definition();
        Initialization();
        setupUI();
    }

    private void Definition() {

    }

    private void Initialization() {
        position = getIntent().getIntExtra("position", position);

        List<ApplicationInfo> applicationInfoList = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

        ApplicationInfo applicationInfo = applicationInfoList.get(position);
        String name = getPackageManager().getApplicationLabel(applicationInfo).toString();
        String packageName = applicationInfo.packageName;
        Drawable drawableIcon = applicationInfo.loadIcon(getPackageManager());
        String sourceDir = applicationInfo.sourceDir;
        Intent launchActivity = getPackageManager().getLaunchIntentForPackage(applicationInfo.packageName);
        String installDate = "";

        try {
            long firstInstallTime = getPackageManager().getPackageInfo(applicationInfo.packageName, 0).firstInstallTime;
            installDate = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]").format(new Date(firstInstallTime));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        detailInformationApplication = new AppInfo(name, packageName, drawableIcon, sourceDir, launchActivity, installDate);


        Log.d(TAG, "Installed package :" + applicationInfo.packageName);
        Log.d(TAG, "Source dir : " + applicationInfo.sourceDir);
        Log.d(TAG, "Launch Activity :" + getPackageManager().getLaunchIntentForPackage(applicationInfo.packageName));
        Log.d(TAG, " Name : " + getPackageManager().getApplicationLabel(applicationInfo).toString());
        Log.d(TAG, "First Install time: " + installDate);


    }

    private void setupUI() {

        tvName.setText(detailInformationApplication.name);
        tvPackage.setText(detailInformationApplication.packageName);
        tvSourceDir.setText(detailInformationApplication.sourceDir);
        tvInstalledDate.setText(detailInformationApplication.installDate);
        ivIcon.setImageDrawable(detailInformationApplication.drawableIcon);


    }


    @OnClick(R.id.bt_open_application)
    public void onViewClicked() {

        Intent intent = detailInformationApplication.launchActivity;
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Toast.makeText(DetailInformation.this, "Launch Activity is null ", Toast.LENGTH_SHORT).show();
        }
    }
}
