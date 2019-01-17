package com.example.testrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    RealmHandle.getInstance().addData(new ExampleData("123"));
    //    RealmHandle.getInstance().addData(new ExampleData("1432"));
    ExampleData exampleData = new ExampleData();
    exampleData.setData("123");
    RealmHandle.getInstance().addData(exampleData);
        Log.d(TAG, "onCreate: " + RealmHandle.getInstance().getExampleDataList());
    }
}
