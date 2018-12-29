package com.tr4n.listinstalledapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MyPC on 05/03/2018.
 */

public class AppAdapter extends BaseAdapter {


    public List<AppInfo> appInfoList;

    public AppAdapter(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return appInfoList.size();
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

        convertView = layoutInflater.inflate(R.layout.item_view, parent, false);

        TextView tvName = convertView.findViewById(R.id.name);
        ImageView ivPicture = convertView.findViewById(R.id.iv_picture);
        TextView tvPackageName = convertView.findViewById(R.id.package_name);

        AppInfo appInfo = appInfoList.get(position);

        tvName.setText(appInfo.name);
        ivPicture.setImageDrawable(appInfo.drawableIcon);
        tvPackageName.setText(appInfo.packageName);

        return convertView;
    }
}
