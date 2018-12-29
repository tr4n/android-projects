package com.example.mypc.cpu_z;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_total_ram)
    TextView tvTotalRam;
    @BindView(R.id.tv_available_ram)
    TextView tvAvailableRam;
    @BindView(R.id.tv_internal_storage)
    TextView tvInternalStorage;
    @BindView(R.id.tv_available_storage)
    TextView tvAvailableStorage;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;

    int countTimes = 10000;
    private final long KILOBYTE = 1024;
    private final int TOTAL = 0, AVAILABLE = 1, USED = 2, PERCENT_AVAIABLE = 3;
    private static final String TAG = "MainActivity";
    @BindView(R.id.bt_get_list_installed_app)
    Button btGetListInstalledApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Definition();
        Initialization();
        setupUI();
    }

    private void setupUI() {

    }

    private void Initialization() {
        tvModel.setText(getDeviceName());
        tvBrand.setText(Build.BRAND);
        tvTotalRam.setText(getTotalRam() + " MB");
        tvInternalStorage.setText(getStorages(TOTAL) + " MB");
        tvAvailableStorage.setText(getStorages(AVAILABLE) + " MB" + "(" + (int) (getStorages(PERCENT_AVAIABLE)) + "%)");
        tvReleaseDate.setText(new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a").format(Build.TIME));


        CountDownTimer countDownTimer = new CountDownTimer(1000000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvAvailableRam.setText(getAvailableRam(false) + " MB(" + getAvailableRam(true) + "%)");
            }

            @Override
            public void onFinish() {
                countTimes = 10000;
            }
        }.start();


    }

    private void Definition() {


    }


    /**
     * Returns the consumer friendly device name
     */
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public int getTotalRam() {
        int tm = 1000;
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/meminfo", "r");
            String load = reader.readLine();
            String[] totrm = load.split(" kB");
            String[] trm = totrm[0].split(" ");
            tm = Integer.parseInt(trm[trm.length - 1]);
            tm = Math.round(tm / 1024);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tm;
    }

    private int getAvailableRam(boolean getPercent) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / 0x100000L;

//Percentage can be calculated for API 16+
        double percentAvail = mi.availMem / (double) mi.totalMem * 100.0;

        return getPercent ? (int) percentAvail : (int) availableMegs;
    }

    private long getStorages(int mode) {
        StatFs internalStatFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long internalTotal;
        long internalFree;

        StatFs externalStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long externalTotal;
        long externalFree;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            internalTotal = (internalStatFs.getBlockCountLong() * internalStatFs.getBlockSizeLong()) / (KILOBYTE * KILOBYTE);
            internalFree = (internalStatFs.getAvailableBlocksLong() * internalStatFs.getBlockSizeLong()) / (KILOBYTE * KILOBYTE);
            externalTotal = (externalStatFs.getBlockCountLong() * externalStatFs.getBlockSizeLong()) / (KILOBYTE * KILOBYTE);
            externalFree = (externalStatFs.getAvailableBlocksLong() * externalStatFs.getBlockSizeLong()) / (KILOBYTE * KILOBYTE);
        } else {
            internalTotal = ((long) internalStatFs.getBlockCount() * (long) internalStatFs.getBlockSize()) / (KILOBYTE * KILOBYTE);
            internalFree = ((long) internalStatFs.getAvailableBlocks() * (long) internalStatFs.getBlockSize()) / (KILOBYTE * KILOBYTE);
            externalTotal = ((long) externalStatFs.getBlockCount() * (long) externalStatFs.getBlockSize()) / (KILOBYTE * KILOBYTE);
            externalFree = ((long) externalStatFs.getAvailableBlocks() * (long) externalStatFs.getBlockSize()) / (KILOBYTE * KILOBYTE);
        }

        long total = internalTotal + externalTotal;
        long free = internalFree + externalFree;
        long used = total - free;

        int percentAvailable = (int) ((100 * free) / total);
        int percentUsed = (int) (used / total);

        return mode == TOTAL ? total : mode == AVAILABLE ? free : mode == USED ? used : mode == PERCENT_AVAIABLE ? percentAvailable : percentUsed;

    }

    private void getListInstalledApp() {
        final PackageManager pm = getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
// the getLaunchIntentForPackage returns an intent that you can use with startActivity()
    }


    @OnClick(R.id.bt_get_list_installed_app)
    public void onViewClicked() {
        CountDownTimer countDownTimer = new CountDownTimer(100, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MainActivity.this, "Loading ...  ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Intent intent =  new Intent(MainActivity.this, ListInstalledAppsActivity.class);
                startActivity(intent);
            }
        }.start();



    }
}
