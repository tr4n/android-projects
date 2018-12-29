package com.example.mypc.cpu_z;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InstalledAppAdapter extends BaseAdapter {

    public List<AppInfo> appInfoList;

    public InstalledAppAdapter(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return appInfoList.isEmpty() ? 0 : appInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        convertView = layoutInflater.inflate(R.layout.installed_app_item, parent, false);

        TextView tvName = convertView.findViewById(R.id.tv_installed_app_name);
        ImageView ivIcon = convertView.findViewById(R.id.iv_installed_app_icon);
        TextView tvPackageName = convertView.findViewById(R.id.tv_installed_app_package);

        AppInfo appInfo = appInfoList.get(position);

        tvName.setText(appInfo.name);
        ivIcon.setImageDrawable(appInfo.drawableIcon);
        tvPackageName.setText(appInfo.packageName);

        return convertView;
    }
}
