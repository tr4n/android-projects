package com.example.mypc.fifteenpuzzle.Activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mypc.fifteenpuzzle.Models.SoundModel;
import com.example.mypc.fifteenpuzzle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionsActivity extends AppCompatActivity {


    @BindView(R.id.iv_main_image)
    ImageView ivMainImage;
    @BindView(R.id.iv_arrow_left)
    ImageView ivArrowLeft;
    @BindView(R.id.iv_arrow_right)
    ImageView ivArrowRight;

    GestureDetector gestureDetector;

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.cl_preview_picture)
    ConstraintLayout clPreviewPicture;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.cl_main_view)
    ConstraintLayout clMainView;


    SoundModel soundModel = new SoundModel(this);

    private int[] idPreview = {
            R.drawable.zero, R.drawable.imageoneqk, R.drawable.imagetwothienanh, R.drawable.imagethreehai,
            R.drawable.imagefourtrung, R.drawable.imagefiveqk, R.drawable.imagesixhoang, R.drawable.imageseven,
            R.drawable.imagetech, R.drawable.imagezero, R.drawable.imageeight, R.drawable.imageninegirl,
            R.drawable.imageten, R.drawable.imageeleven, 0
    };
    private int currentPosition = 1;
    private final int NEXT_TO_LEFT = -1;
    private final int NEXT_TO_RIGHT = 1;
    private final int NUM_PREVIEWS = 14;
    private static final String TAG = "OptionsActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Define();
        Initialization();
        setupUI();


    }

    @OnClick({ R.id.iv_arrow_left, R.id.iv_arrow_right, R.id.iv_check, R.id.iv_back, R.id.iv_right, R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_arrow_left:

                changePicture(NEXT_TO_LEFT);
                break;
            case R.id.iv_arrow_right:
                changePicture(NEXT_TO_RIGHT);

                break;
            case R.id.iv_back:
                soundModel.playSound(R.raw.snapping);
                onBackPressed();
                break;
            case R.id.iv_check:
                soundModel.playSound(R.raw.snapping);
                Intent intentSave = new Intent(OptionsActivity.this, GameActivity.class);
                intentSave.putExtra("PositionOfMainImage", currentPosition);
                startActivity(intentSave);
                break;
            case R.id.iv_right:
                changePicture(NEXT_TO_RIGHT);
                break;
            case R.id.iv_left:
                changePicture(NEXT_TO_LEFT);
                break;

        }
    }

    private void Define() {


    }

    private void Initialization() {

        currentPosition = 1;
        ivMainImage.setImageResource(idPreview[1]);
        ivMain.setImageResource(idPreview[1]);
        ivLeft.setImageResource(idPreview[0]);
        ivRight.setImageResource(idPreview[2]);

    }

    private void setupUI() {

        gestureDetector = new GestureDetector(this, new myGestureDetector());
        clMainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        clPreviewPicture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


    }

    private boolean changePicture(final int dir) {

        soundModel.playSound(R.raw.snapping);

        currentPosition = (dir + currentPosition + NUM_PREVIEWS) % NUM_PREVIEWS;
        ivMainImage.setImageResource(idPreview[currentPosition]);
        ivMain.setImageResource(idPreview[currentPosition]);
        ivLeft.setImageResource(idPreview[(NUM_PREVIEWS - 1 + currentPosition) % NUM_PREVIEWS]);
        ivRight.setImageResource(idPreview[(NUM_PREVIEWS + 1 + currentPosition) % NUM_PREVIEWS]);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class myGestureDetector implements GestureDetector.OnGestureListener {
        final int SWIP_VELOCITY = 50;
        final int SWIP_THERSHOLD = 50;


        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {

            float fromX = motionEvent1.getX();
            float toX = motionEvent2.getX();


            if (Math.abs(velocityX) > SWIP_VELOCITY) {
                if (fromX - toX > SWIP_THERSHOLD) {
                    changePicture(NEXT_TO_RIGHT);
                    Log.d(TAG, "onFling: " + "RIGHT TO LEFT");
                    // Toast.makeText(OptionsActivity.this, "Right to Left", Toast.LENGTH_SHORT).show();
                }
                if (toX - fromX > SWIP_THERSHOLD) {

                    Log.d(TAG, "onFling: " + "LEFT TO RIGHT");
                    changePicture(NEXT_TO_LEFT);
                    //   Toast.makeText(OptionsActivity.this, "Left to Right", Toast.LENGTH_SHORT).show();
                }
            }


            return true;
        }
    }


}