package com.tr4n.superstar2018;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;


public class Settings extends Activity {
    private Sound sound = new Sound(this);
    private GestureDetector gestureDetector;
    private LinearLayout LayoutContainSave, LayoutContainSound, LayoutContainMusic;
    private LinearLayout LayoutContainMainBoxs;
    private ImageView ImageViewSound, ImageViewSave, ImageViewMusic;
    private ImageView[] ImageViewControlLeft = new ImageView[5];
    private ImageView[] ImageViewControlRight = new ImageView[5];
    private ImageView[] ImageViewControlUp = new ImageView[5];
    private ImageView[] ImageViewControlDown = new ImageView[5];
    private ImageView[] ImageViewBoxSetting = new ImageView[5];
    private int[] StatusBoxSetting = new int[5];
    public int[] ID_OF_CHARACTER = {R.drawable.characterstar, R.drawable.starcharacter,
            R.drawable.characterbee, R.drawable.charactergreen, R.drawable.characterred, 0};
    private int[] ID_OF_COIN = {R.drawable.numberzeroblue, R.drawable.numberoneblue, R.drawable.numbertwoblue, R.drawable.numberthreeblue,
            R.drawable.numberfourblue, R.drawable.numberfiveblue, R.drawable.numbersixblue, R.drawable.numbersevenblue, R.drawable.numbereightblue, R.drawable.numbernineblue,
            R.drawable.numbertenblue, R.drawable.numberelevenblue, R.drawable.numbertwelveblue, R.drawable.numberthirdteenblue, R.drawable.numberfourteenblue, 0};
    private int[] ID_OF_THEME = {R.drawable.numberzerogreen, R.drawable.numberonegreen, R.drawable.numbertwogreen, R.drawable.numberthreegreen, R.drawable.numberfourgreen,
            R.drawable.numberfivegreen, R.drawable.numbersixgreen, R.drawable.numbersevengreen, R.drawable.numbereightgreen, R.drawable.numberninegreen,
            R.drawable.numbertengreen, R.drawable.numberelevengreen, R.drawable.numbertwelvegreen, R.drawable.numberthirdteengreen, R.drawable.numberfourteengreen, 0};
    public final int NUMBER_OF_CHARACTERS = 5;
    public final int NUMBER_OF_BOXSETTINGS = 3;
    public final int NUMBER_OF_COINS = 15;
    public final int NUMBER_OF_THEMES = 15;
    private final int ID_OF_SOUND = R.raw.snapping;
    private final int ID_OF_MUSIC = R.raw.backgroundtwo;
    private final int SWIP_VELOCITY = 100;
    private final int SWIP_THERSHOLD = 100;
    private final int ID_OF_CONTROLLEFT = R.drawable.controlleft;
    private final int ID_OF_CONTROLDOWN = R.drawable.controldown;
    private final int ID_OF_CONTROLRIGHT = R.drawable.controlright;
    private final int ID_OF_CONTROLUP = R.drawable.controlup;
    private final int CHARACTER_BOX = 1, COIN_BOX = 2, THEME_BOX = 3;
    private final int UP_TO_DOWN = 3;
    private final int DOWN_TO_UP = 1;
    private final int LEFT_TO_RIGHT = 0;
    private final int RIGHT_TO_LEFT = 2;
    private int TurnOnSound = 1, TurnOnMusic = 1;
    private int PositionOfBoxSetting = 1;
    private int SavingCondition = 0;


    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        Define();
        CallBackBundle();
        Initialization();
        LayoutSetOnClick();
    }

    private void Define() {
        LayoutContainMainBoxs = (LinearLayout) findViewById(R.id.LayoutContainMainBoxs);
        LayoutContainSave = (LinearLayout) findViewById(R.id.LayoutContainSave);
        LayoutContainSound = (LinearLayout) findViewById(R.id.LayoutContainSound);
        LayoutContainMusic = (LinearLayout) findViewById(R.id.LayoutContainMusic);
        ImageViewSound = (ImageView) findViewById(R.id.ImageViewSound);
        ImageViewSave = (ImageView) findViewById(R.id.ImageViewSave);
        ImageViewMusic = (ImageView) findViewById(R.id.ImageViewMusic);

        ImageViewControlUp[1] = (ImageView) findViewById(R.id.ImageViewControlUp1);
        ImageViewControlDown[1] = (ImageView) findViewById(R.id.ImageViewControlDown1);
        ImageViewControlLeft[1] = (ImageView) findViewById(R.id.ImageViewControlLeft1);
        ImageViewControlRight[1] = (ImageView) findViewById(R.id.ImageViewControlRight1);
        ImageViewBoxSetting[1] = (ImageView) findViewById(R.id.ImageViewBox1);
        ImageViewControlUp[2] = (ImageView) findViewById(R.id.ImageViewControlUp2);
        ImageViewControlDown[2] = (ImageView) findViewById(R.id.ImageViewControlDown2);
        ImageViewControlLeft[2] = (ImageView) findViewById(R.id.ImageViewControlLeft2);
        ImageViewControlRight[2] = (ImageView) findViewById(R.id.ImageViewControlRight2);
        ImageViewBoxSetting[2] = (ImageView) findViewById(R.id.ImageViewBox2);
        ImageViewControlUp[3] = (ImageView) findViewById(R.id.ImageViewControlUp3);
        ImageViewControlDown[3] = (ImageView) findViewById(R.id.ImageViewControlDown3);
        ImageViewControlLeft[3] = (ImageView) findViewById(R.id.ImageViewControlLeft3);
        ImageViewControlRight[3] = (ImageView) findViewById(R.id.ImageViewControlRight3);
        ImageViewBoxSetting[3] = (ImageView) findViewById(R.id.ImageViewBox3);

    }

    private void ChangingScreen() {

        LayoutContainSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
                if (SavingCondition == 0) {
                    SavingCondition++;
                    ImageViewSave.setImageResource(R.drawable.saveorange);
                    return;
                }

                if (TurnOnMusic > 0) sound.turnOffBackgroundSound(ID_OF_MUSIC);

                Intent intent = new Intent(Settings.this, MainGame.class);

                Bundle bundle = new Bundle();
                bundle.putInt("TurnOnSound", TurnOnSound);
                bundle.putInt("TurnOnMusic", TurnOnMusic);
                bundle.putInt("ID_OF_CHARACTER", ID_OF_CHARACTER[ID_OF_CHARACTER[NUMBER_OF_CHARACTERS]]);
                bundle.putInt("NUMBER_STAR", ID_OF_COIN[NUMBER_OF_COINS]);
                bundle.putInt("ID_OF_THEMES", ID_OF_THEME[NUMBER_OF_THEMES]);
                intent.putExtra("BundleFromSettingsToMainGame", bundle);
                startActivity(intent);
            }
        });
    }

    private void CallBackBundle() {
        Intent intent = getIntent();
        Bundle BundleFromMainActivity = intent.getBundleExtra("BundleFromMainActivity");
        TurnOnSound = BundleFromMainActivity.getInt("TurnOnSound");
        TurnOnMusic = BundleFromMainActivity.getInt("TurnOnMusic");


    }

    private void Initialization() {
        if (TurnOnSound > 0) ImageViewSound.setImageResource(R.drawable.soundgreen);
        else ImageViewSound.setImageResource(R.drawable.soundgray);
        if (TurnOnMusic > 0) ImageViewMusic.setImageResource(R.drawable.musicgreen);
        else ImageViewMusic.setImageResource(R.drawable.musicgray);
        ImageViewSave.setImageResource(R.drawable.saveblack);
        if (TurnOnMusic > 0) {
            sound.turnOnBackgroundSound(ID_OF_MUSIC);
        }
        SetBoxSettings();
        SetGestureDetector();
    }

    private void LayoutSetOnClick() {

        ChangingScreen();

        LayoutContainMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnOnMusic = 1 - TurnOnMusic;
                if (TurnOnMusic > 0) sound.turnOnBackgroundSound(ID_OF_MUSIC);
                else sound.turnOffBackgroundSound(ID_OF_MUSIC);
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
                if (TurnOnMusic > 0) ImageViewMusic.setImageResource(R.drawable.musicgreen);
                else ImageViewMusic.setImageResource(R.drawable.musicgray);

                if (SavingCondition == 0) {
                    SavingCondition++;
                    ImageViewSave.setImageResource(R.drawable.saveorange);
                }
            }
        });

        LayoutContainSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnOnSound = 1 - TurnOnSound;
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
                if (TurnOnSound > 0) ImageViewSound.setImageResource(R.drawable.soundgreen);
                else ImageViewSound.setImageResource(R.drawable.soundgray);
                if (SavingCondition == 0) {
                    SavingCondition++;
                    ImageViewSave.setImageResource(R.drawable.saveorange);
                }

            }
        });
    }

    private void SetBoxSettings() {
        Arrays.fill(StatusBoxSetting, 0);
        StatusBoxSetting[PositionOfBoxSetting] = 1;
        ImageViewBoxSetting[1].setImageResource(ID_OF_CHARACTER[ID_OF_CHARACTER[NUMBER_OF_CHARACTERS]]);
        ImageViewBoxSetting[2].setImageResource(ID_OF_COIN[ID_OF_COIN[NUMBER_OF_COINS]]);
        ImageViewBoxSetting[3].setImageResource(ID_OF_THEME[ID_OF_THEME[NUMBER_OF_THEMES]]);

        for (int i = 1; i <= NUMBER_OF_BOXSETTINGS; i++) {
            if (StatusBoxSetting[i] == 0) {
                ImageViewControlDown[i].setImageResource(0);
                ImageViewControlUp[i].setImageResource(0);
                ImageViewControlLeft[i].setImageResource(0);
                ImageViewControlRight[i].setImageResource(0);
            } else {
                ImageViewControlDown[i].setImageResource(ID_OF_CONTROLDOWN);
                ImageViewControlUp[i].setImageResource(ID_OF_CONTROLUP);
                ImageViewControlLeft[i].setImageResource(ID_OF_CONTROLLEFT);
                ImageViewControlRight[i].setImageResource(ID_OF_CONTROLRIGHT);

            }
        }
    }

    private void SetGestureDetector() {
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        LayoutContainMainBoxs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float fromX = e1.getX();
            float fromY = e1.getY();
            float toX = e2.getX();
            float toY = e2.getY();
            int control = 0; // 0 : Right ; 1 : Up ; 2 : Left ; 3 : Down
            if (toX > fromX) {
                if (fromX - toX < toY - fromY && toY - fromY < toX - fromX)
                    control = LEFT_TO_RIGHT;
                else if (toY > fromY)
                    control = UP_TO_DOWN;
                else
                    control = DOWN_TO_UP;
            } else {
                if (fromX - toX > toY - fromY && toY - fromY > toX - fromX)
                    control = RIGHT_TO_LEFT;
                else if (toY > fromY)
                    control = UP_TO_DOWN;
                else
                    control = DOWN_TO_UP;
            }
            if (Math.abs(velocityX) > SWIP_VELOCITY && (control == LEFT_TO_RIGHT || control == RIGHT_TO_LEFT)) {
                if (e1.getX() - e2.getX() > SWIP_THERSHOLD) {
                    SetNextObject(LEFT_TO_RIGHT);
                }
                if (e2.getX() - e1.getX() > SWIP_THERSHOLD) {
                    SetNextObject(RIGHT_TO_LEFT);
                }
            }
            if (Math.abs(velocityY) > SWIP_VELOCITY && (control == DOWN_TO_UP || control == UP_TO_DOWN)) {
                if (e1.getY() - e2.getY() > SWIP_THERSHOLD) {
                    SetNextObject(DOWN_TO_UP);
                }
                if (e2.getY() - e1.getY() > SWIP_THERSHOLD) {
                    SetNextObject(UP_TO_DOWN);
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private void SetNextObject(final int DIRECT) {
        if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
        if (SavingCondition == 0) {
            SavingCondition++;
            ImageViewSave.setImageResource(R.drawable.saveorange);
        }
        switch (DIRECT) {
            case UP_TO_DOWN:
                PositionOfBoxSetting++;
                if (PositionOfBoxSetting > NUMBER_OF_BOXSETTINGS) PositionOfBoxSetting = 1;
                SetBoxSettings();
                break;

            case DOWN_TO_UP:
                PositionOfBoxSetting--;
                if (PositionOfBoxSetting < 1) PositionOfBoxSetting = NUMBER_OF_BOXSETTINGS;
                SetBoxSettings();
                break;
            case LEFT_TO_RIGHT:
                switch (PositionOfBoxSetting) {
                    case CHARACTER_BOX:
                        ID_OF_CHARACTER[NUMBER_OF_CHARACTERS] = (--ID_OF_CHARACTER[NUMBER_OF_CHARACTERS] + NUMBER_OF_CHARACTERS * NUMBER_OF_CHARACTERS) % NUMBER_OF_CHARACTERS;
                        ImageViewBoxSetting[CHARACTER_BOX].setImageResource(ID_OF_CHARACTER[ID_OF_CHARACTER[NUMBER_OF_CHARACTERS]]);
                        break;
                    case COIN_BOX:
                        ID_OF_COIN[NUMBER_OF_COINS] = (--ID_OF_COIN[NUMBER_OF_COINS] + NUMBER_OF_COINS * NUMBER_OF_COINS) % NUMBER_OF_COINS;
                        ImageViewBoxSetting[COIN_BOX].setImageResource(ID_OF_COIN[ID_OF_COIN[NUMBER_OF_COINS]]);
                        break;
                    case THEME_BOX:
                        ID_OF_THEME[NUMBER_OF_THEMES] = (--ID_OF_THEME[NUMBER_OF_THEMES] + NUMBER_OF_THEMES * NUMBER_OF_THEMES) % NUMBER_OF_THEMES;
                        ImageViewBoxSetting[THEME_BOX].setImageResource(ID_OF_THEME[ID_OF_THEME[NUMBER_OF_THEMES]]);
                        break;
                }
                break;
            case RIGHT_TO_LEFT:
                switch (PositionOfBoxSetting) {
                    case CHARACTER_BOX:
                        ID_OF_CHARACTER[NUMBER_OF_CHARACTERS] = (++ID_OF_CHARACTER[NUMBER_OF_CHARACTERS] + NUMBER_OF_CHARACTERS * NUMBER_OF_CHARACTERS) % NUMBER_OF_CHARACTERS;
                        ImageViewBoxSetting[CHARACTER_BOX].setImageResource(ID_OF_CHARACTER[ID_OF_CHARACTER[NUMBER_OF_CHARACTERS]]);
                        break;
                    case COIN_BOX:
                        ID_OF_COIN[NUMBER_OF_COINS] = (++ID_OF_COIN[NUMBER_OF_COINS] + NUMBER_OF_COINS * NUMBER_OF_COINS) % NUMBER_OF_COINS;
                        ImageViewBoxSetting[COIN_BOX].setImageResource(ID_OF_COIN[ID_OF_COIN[NUMBER_OF_COINS]]);
                        break;
                    case THEME_BOX:
                        ID_OF_THEME[NUMBER_OF_THEMES] = (++ID_OF_THEME[NUMBER_OF_THEMES] + NUMBER_OF_THEMES * NUMBER_OF_THEMES) % NUMBER_OF_THEMES;
                        ImageViewBoxSetting[THEME_BOX].setImageResource(ID_OF_THEME[ID_OF_THEME[NUMBER_OF_THEMES]]);
                        break;
                }
                break;
        }
    }


}
