package com.example.mypc.testapp;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mypc.testapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String m_root = Environment.getExternalStorageDirectory().getPath();
    private ListView m_RootList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDirFromRoot(m_root);
        m_RootList = (ListView) findViewById(R.id.rl_lvListRoot);
    }

    public void getDirFromRoot(String p_rootPath) {
        ArrayList<String> m_item = new ArrayList<String>();
        Boolean m_isRoot = true;
        final ArrayList<String> m_path = new ArrayList<String>();
        ArrayList<String> m_files = new ArrayList<String>();
        ArrayList<String> m_filesPath = new ArrayList<String>();
        File m_file = new File(p_rootPath);
        File[] m_filesArray = m_file.listFiles();
        if (!p_rootPath.equals(m_root)) {
            m_item.add("../");
            m_path.add(m_file.getParent());
            m_isRoot = false;
        }
        String m_curDir = p_rootPath;
        //sorting file list in alphabetical order
        Arrays.sort(m_filesArray);


        for (int i = 0; i < m_filesArray.length; i++) {
            File file = m_filesArray[i];
            if (file.isDirectory()) {
                m_item.add(file.getName());
                m_path.add(file.getPath());
            } else {
                m_files.add(file.getName());
                m_filesPath.add(file.getPath());
            }
        }
        for (String m_AddFile : m_files) {
            m_item.add(m_AddFile);
        }
        for (String m_AddPath : m_filesPath) {
            m_path.add(m_AddPath);
        }





        MyAdapter m_listAdapter = new MyAdapter(this, m_item, m_path, m_isRoot);
        m_RootList.setAdapter(m_listAdapter);
        m_RootList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                File m_isFile = new File(m_path.get(position));
                if (m_isFile.isDirectory()) {
                    getDirFromRoot(m_isFile.toString());
                } else {
                    //Toast.makeText(MainActivity.this, "This is File", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}