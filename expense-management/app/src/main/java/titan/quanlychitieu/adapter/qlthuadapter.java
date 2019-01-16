package titan.quanlychitieu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import titan.quanlychitieu.R;
import titan.quanlychitieu.models.ModelThu;

/**
 * Created by titan on 17/06/2017.
 */

public class qlthuadapter extends ArrayAdapter<ModelThu> {

    private Context context;
    private int resource;
    private ArrayList<ModelThu> arraythu = new ArrayList<ModelThu>();

    public qlthuadapter(Context context, int resource, ArrayList<ModelThu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arraythu = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if(convertView==null){
            viewholder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.thuchi_listview_item,parent,false);
            viewholder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            viewholder.item_tenkhoan = (TextView) convertView.findViewById(R.id.item_tenkhoan);
            viewholder.item_nguoitao = (TextView) convertView.findViewById(R.id.item_nguoitao);
            viewholder.item_ngaytao = (TextView) convertView.findViewById(R.id.item_ngaytao);
            viewholder.item_sotien = (TextView) convertView.findViewById(R.id.item_sotien);
            viewholder.item_ghichu = (TextView) convertView.findViewById(R.id.item_ghichu);
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.imgAvatar.setBackgroundResource(R.drawable.plus);
        ModelThu modelthu = arraythu.get(position);
        viewholder.item_tenkhoan.setText(modelthu.getTenkhoanthu());
        viewholder.item_nguoitao.setText(modelthu.getNguoitao());
        viewholder.item_ngaytao.setText(modelthu.getNgaytao());
        viewholder.item_sotien.setText(modelthu.getSotien());
        viewholder.item_ghichu.setText(modelthu.getGhichu());
        return convertView;
    }
    public class ViewHolder{
        public ImageView imgAvatar;
        public TextView item_tenkhoan;
        public TextView item_nguoitao;
        public TextView item_ngaytao;
        public TextView item_sotien;
        public TextView item_ghichu;

    }
}
