package com.tr4n.listinstalledapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by MyPC on 05/03/2018.
 */

public class AppInfo implements Serializable {

    public String name;
    public String packageName;
    public Drawable drawableIcon;
    public String sourceDir;
    public Intent launchActivity;
    public String installDate;

    public AppInfo(String name, String packageName, Drawable drawableIcon, String sourceDir, Intent launchActivity, String installDate) {
        this.name = name;
        this.packageName = packageName;
        this.drawableIcon = drawableIcon;
        this.sourceDir = sourceDir;
        this.launchActivity = launchActivity;
        this.installDate = installDate;
    }
}
