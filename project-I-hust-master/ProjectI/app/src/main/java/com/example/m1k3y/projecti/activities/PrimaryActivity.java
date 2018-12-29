package com.example.m1k3y.projecti.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.m1k3y.projecti.R;

public class PrimaryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_primary);
        getSupportActionBar().hide();

        ImageView ivFlag = findViewById(R.id.iv_flag);

        int fixedWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                fixedWidth,
                fixedWidth
        );
        ivFlag.setLayoutParams(layoutParams);

        findViewById(R.id.iv_flag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimaryActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
