package com.example.mypc.fifteenpuzzle.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mypc.fifteenpuzzle.Models.SoundModel;
import com.example.mypc.fifteenpuzzle.R;

public class IntroductionActivity extends AppCompatActivity {

    private ImageView ivBack;
    private View vCancel, vNextLeft, vNextRight;
    private ConstraintLayout clTable ;
    private Context context;
    private int[] idIntroductions = {R.drawable.introductionone, R.drawable.introductiontwo};
    private int currentPosition = 0;
    private final int NUM_Papers = 2;
    private SoundModel soundModel = new SoundModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introduction);
        getSupportActionBar().hide();

        Define();
        Initialization();

    }

    private void Define(){
        ivBack = findViewById(R.id.iv_back);
        vCancel = findViewById(R.id.v_cancel);
        vNextLeft = findViewById(R.id.v_next_left);
        vNextRight = findViewById(R.id.v_next_right);
        clTable = findViewById(R.id.cl_table);
    }

    private void Initialization(){
        currentPosition = 0;
        context  = IntroductionActivity.this;
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundModel.playSound(R.raw.snapping);
                Intent intentBack = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(intentBack);

            }
        });
        vCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundModel.playSound(R.raw.snapping);
                Intent intentCancel = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(intentCancel);
            }
        });
        vNextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundModel.playSound(R.raw.snapping);
                currentPosition = (++currentPosition) % NUM_Papers;
                clTable.setBackgroundResource(idIntroductions[currentPosition]);

            }
        });
        vNextLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundModel.playSound(R.raw.snapping);
                currentPosition = (--currentPosition + NUM_Papers) % NUM_Papers;
                clTable.setBackgroundResource(idIntroductions[currentPosition]);
            }
        });
    }





}