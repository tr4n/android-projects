package com.example.mypc.a15puzzlegametechkids;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

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

    Sound sound = new Sound(this);

    private int[] idPreview = {R.drawable.imagezero, R.drawable.imageone, R.drawable.imagetwo, R.drawable.imagethree, R.drawable.tnhfour, R.drawable.tnhone, R.drawable.tnhtwo, R.drawable.imageone, R.drawable.tnhone, R.drawable.tnhtwo, 0};
    private int currentPosition = 1;
    private final int NEXT_TO_LEFT = -1;
    private final int NEXT_TO_RIGHT = 1;
    private final int NUM_PREVIEWS = 4;
    private static final String TAG = "OptionsActivity";
    private boolean[] onTouchable = new boolean[4];


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

    @OnClick({ R.id.iv_arrow_left, R.id.iv_arrow_right, R.id.iv_check, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_arrow_left:

                changePicture(NEXT_TO_LEFT);
                break;
            case R.id.iv_arrow_right:

                changePicture(NEXT_TO_RIGHT);

                break;
            case R.id.iv_back:
                sound.playSound(R.raw.snapping);
                Intent intentBack = new Intent(OptionsActivity.this, MainGameActivity.class);
                intentBack.putExtra("PositionOfMainImage", 1);
                startActivity(intentBack);
                break;
            case R.id.iv_check:
                sound.playSound(R.raw.snapping);
                Intent intentSave = new Intent(OptionsActivity.this, MainGameActivity.class);
                intentSave.putExtra("PositionOfMainImage", currentPosition);
                startActivity(intentSave);
                break;

        }
    }

    private void Define() {


    }

    private void Initialization() {
        for(int i = 0 ;i < 3 ;i ++)
            onTouchable[i] = true;
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
                if(!onTouchable[0] || !onTouchable[1] || !onTouchable[2]) return false;
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


    }

    private boolean changePicture(final int dir) {
        if(!onTouchable[0] || !onTouchable[1] || !onTouchable[2]) return false;
        sound.playSound(R.raw.snapping);

        //  clPreviewPicture.setVisibility(View.VISIBLE);
        currentPosition = (dir + currentPosition + NUM_PREVIEWS) % NUM_PREVIEWS;
        ivMainImage.setImageResource(idPreview[currentPosition]);
        ivMain.setImageResource(idPreview[currentPosition]);
        ivLeft.setImageResource(idPreview[(NUM_PREVIEWS - 1 + currentPosition) % NUM_PREVIEWS]);
        ivRight.setImageResource(idPreview[(NUM_PREVIEWS + 1 + currentPosition) % NUM_PREVIEWS]);

        return true;
    }


    private class myGestureDetector implements GestureDetector.OnGestureListener {
        final int RIGHT_QUARTER = 0;
        final int UP_QUARTER = 1;
        final int LEFT_QUARTER = 2;
        final int DOWN_QUARTER = 3;
        final int SWIP_VELOCITY = 100;
        final int SWIP_THERSHOLD = 100;


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
