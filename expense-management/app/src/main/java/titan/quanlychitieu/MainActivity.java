package titan.quanlychitieu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.lang.reflect.Type;

import titan.quanlychitieu.adapter.qlchiadapter;
import titan.quanlychitieu.adapter.qlthuadapter;
import titan.quanlychitieu.models.ModelChi;
import titan.quanlychitieu.models.ModelThu;

public class MainActivity extends AppCompatActivity {
     ArrayList<ModelThu> arraythu;
     ArrayList<ModelChi> arraychi;
     EditText edittenkhoan;
     EditText editnguoitao;
     EditText editngaytao;
     EditText editsotien;
     EditText editghichu;
    TextView lbltongtien;
    int positionItem;
    int tongtien;
    Dialog dialogItem;
    Button btnthemthu;
     Button btnthemchi;
     ListView lvthu;
     ListView lvchi;
     qlthuadapter thuadapter ;
     qlchiadapter chiadapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        arraythu = new ArrayList<ModelThu>();
        arraychi = new ArrayList<ModelChi>();
        loaddulieu();
        lbltongtien.setText("Số dư: "+tinhtien()+" VNĐ");

        thuadapter = new qlthuadapter(this,R.layout.thuchi_listview_item,arraythu);
        lvthu.setAdapter(thuadapter);
        lvthu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setDialogDeleteItemThu(position);
            }
        });
        chiadapter = new qlchiadapter(this,R.layout.thuchi_listview_item,arraychi);
        lvchi.setAdapter(chiadapter);
        lvchi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setDialogDeleteItemChi(position);
            }
        });



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       savedulieu();

    }


    public void getWidget(){
        lbltongtien = (TextView) findViewById(R.id.txttongtien);
        edittenkhoan = (EditText) findViewById(R.id.editText1);
        editnguoitao = (EditText) findViewById(R.id.editText2);
        editngaytao = (EditText) findViewById(R.id.editText3);
        editsotien = (EditText) findViewById(R.id.editText4);
        editghichu = (EditText) findViewById(R.id.editText5);
        btnthemthu = (Button) findViewById(R.id.button2);
        btnthemchi = (Button) findViewById(R.id.button);
        lvthu = (ListView) findViewById(R.id.lvthu);
        lvchi = (ListView) findViewById(R.id.lvchi);
    }
    public void resetWidget(){
        edittenkhoan.setText("");
        editnguoitao.setText("");
        editngaytao.setText("");
        editsotien.setText("");
        editghichu.setText("");
    }

    public void addthu(View view){
        if(view.getId()==R.id.button2){
            String tenkhoan = edittenkhoan.getText().toString().trim();
            String nguoitao = editnguoitao.getText().toString().trim();
            String ngaytao = editngaytao.getText().toString().trim();
           String sotien =  editsotien.getText().toString().trim();
            String ghichu = editghichu.getText().toString().trim();


            if(TextUtils.isEmpty(tenkhoan) || TextUtils.isEmpty(nguoitao)|| TextUtils.isEmpty(ngaytao)){
                Toast.makeText(this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else{

           ModelThu mthu = new ModelThu(tenkhoan,nguoitao,ngaytao,sotien,ghichu);
           arraythu.add(mthu);
          lbltongtien.setText("Số dư: "+tinhtien()+" VNĐ");
          resetWidget();
            }
            thuadapter.notifyDataSetChanged();

        }
    }
    public void addchi(View view){
        if(view.getId()==R.id.button){
            String tenkhoan = edittenkhoan.getText().toString().trim();
            String nguoitao = editnguoitao.getText().toString().trim();
            String ngaytao = editngaytao.getText().toString().trim();
            String sotien =  editsotien.getText().toString().trim();
            String ghichu = editghichu.getText().toString().trim();


            if(TextUtils.isEmpty(tenkhoan) || TextUtils.isEmpty(nguoitao)|| TextUtils.isEmpty(ngaytao)){
                Toast.makeText(this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else{

                ModelChi mchi = new ModelChi(tenkhoan,nguoitao,ngaytao,sotien,ghichu);
                arraychi.add(mchi);
                lbltongtien.setText("Số dư: "+tinhtien()+" VNĐ");
                resetWidget();
            }
            chiadapter.notifyDataSetChanged();
        }
    }



    private void setDialogDeleteItemThu(int position){
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setTitle("Delete Item");
        ab.setMessage("Do you want to delete this item ?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                arraythu.remove(positionItem);
                lbltongtien.setText("Số dư: "+tinhtien()+" VNĐ");
                thuadapter.notifyDataSetChanged();
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        ab.show();
    }

    private void setDialogDeleteItemChi(int position){
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setTitle("Delete Item");
        ab.setMessage("Do you want to delete this item ?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                arraychi.remove(positionItem);
                lbltongtien.setText("Số dư: "+tinhtien()+" VNĐ");
               chiadapter.notifyDataSetChanged();
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        ab.show();
    }


    public int tinhtien(){

        int i;
        int sum = 0;
        for(i = 0; i < arraythu.size(); i++)
            sum += Integer.parseInt(arraythu.get(i).getSotien());

        for(i = 0; i < arraychi.size(); i++)
            sum -= Integer.parseInt(arraychi.get(i).getSotien());

        return sum;

    }

    public void savedulieu(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        Gson gson2 = new Gson();
        String json = gson.toJson(arraythu);
        String json2 = gson2.toJson(arraychi);
        editor.putString("arrthu", json);
        editor.putString("arrchi", json2);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
    }
    public void loaddulieu(){


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("arrthu","");
        if (!json.isEmpty()) {
            Type type = new TypeToken<ArrayList<ModelThu>>(){}.getType();
            arraythu= gson.fromJson(json, type);
        }


        Gson gson2 = new Gson();
        String json2 = sharedPrefs.getString("arrchi", "");
        if (!json2.isEmpty()) {
            Type type2 = new TypeToken<ArrayList<ModelChi>>() {}.getType();
            arraychi = gson2.fromJson(json2, type2);
        }

    }

}
