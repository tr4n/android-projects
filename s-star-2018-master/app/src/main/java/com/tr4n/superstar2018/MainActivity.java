package com.tr4n.superstar2018;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tr4n.superstar2018.R;

public class MainActivity extends Activity {

    private LinearLayout LayoutContainPlaygame, LayoutContainFacebook, LayoutContainHelp;
    private LinearLayout LayoutContainSound, LayoutContainMusic;
    private ImageView ImageViewSound, ImageViewMusic;
    private int ID_OF_SOUND = R.raw.snapping;
    private int ID_OF_MUSIC = R.raw.backgroundtwo;
    private Sound sound = new Sound(this);
    private int TurnOnSound = 1, TurnOnMusic = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Define();
        if (TurnOnMusic > 0) sound.turnOnBackgroundSound(ID_OF_MUSIC);
        LayoutSetOnClick();

    }

    private void Define() {
        LayoutContainPlaygame = (LinearLayout) findViewById(R.id.LayoutContainPlaygame);
        LayoutContainFacebook = (LinearLayout) findViewById(R.id.LayoutContainFacebook);
        LayoutContainHelp = (LinearLayout) findViewById(R.id.LayoutContainHelp);
        LayoutContainSound = (LinearLayout) findViewById(R.id.LayoutContainSound);
        LayoutContainMusic = (LinearLayout) findViewById(R.id.LayoutContainMusic);
        ImageViewMusic = (ImageView) findViewById(R.id.ImageViewMusic);
        ImageViewSound = (ImageView) findViewById(R.id.ImageViewSound);
    }

    private void LayoutSetOnClick() {
        ChangingScreen();
        LayoutContainFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
            }
        });

        LayoutContainHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
            }
        });

        LayoutContainSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnOnSound = 1 - TurnOnSound;
                int ID_OF_IMAGEVIEWSOUND = TurnOnSound > 0 ? R.drawable.soundorange : R.drawable.soundgray;
                ImageViewSound.setImageResource(ID_OF_IMAGEVIEWSOUND);
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);


            }
        });

        LayoutContainMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnOnMusic = 1 - TurnOnMusic;
                int ID_OF_IMAGEVIEWMUSIC = TurnOnMusic > 0 ? R.drawable.musicorange : R.drawable.musicgray;
                ImageViewMusic.setImageResource(ID_OF_IMAGEVIEWMUSIC);
                if (TurnOnSound > 0) sound.playSound(ID_OF_SOUND);
                if (TurnOnMusic > 0) {
                    sound.turnOnBackgroundSound(ID_OF_MUSIC);
                } else {
                    if (sound.BackgroundSound.isPlaying())
                        sound.turnOffBackgroundSound(ID_OF_MUSIC);
                }
            }
        });
    }

    private void ChangingScreen() {

        LayoutContainPlaygame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                Bundle bundle = new Bundle();
                if (TurnOnMusic > 0) sound.turnOffBackgroundSound(ID_OF_MUSIC);

                bundle.putInt("TurnOnSound", TurnOnSound);
                bundle.putInt("TurnOnMusic", TurnOnMusic);
                intent.putExtra("BundleFromMainActivity", bundle);
                startActivity(intent);
            }
        });


    }



}
