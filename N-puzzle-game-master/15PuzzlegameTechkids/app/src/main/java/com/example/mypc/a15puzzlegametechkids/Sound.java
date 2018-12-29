package com.example.mypc.a15puzzlegametechkids;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by MyPC on 31/03/2018.
 */

public class Sound {


    private MediaPlayer SystemSound;
    public MediaPlayer BackgroundSound ;
    private Context context;


    public Sound(Context context) {
        this.context = context;

    }

    public void playSound(final int ID_OF_SOUND) {
        final MediaPlayer sound = MediaPlayer.create(context, ID_OF_SOUND);
        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
        } else
            sound.start();

        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sound.release();

            }
        });
    }

    public void turnOnBackgroundSound(final int ID_OF_SOUND) {

        BackgroundSound = MediaPlayer.create(context, ID_OF_SOUND);
        if (BackgroundSound.isPlaying()) {
            BackgroundSound.stop();
            BackgroundSound.release();
        } else
            BackgroundSound.start();

        BackgroundSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                BackgroundSound.release();
                BackgroundSound = MediaPlayer.create(context, ID_OF_SOUND);
            }
        });
    }

    public void turnOffBackgroundSound(int ID_OF_SOUND) {

        if (!BackgroundSound.isPlaying()) return;
        BackgroundSound.stop();
        BackgroundSound.release();
        BackgroundSound = MediaPlayer.create(context, ID_OF_SOUND);

    }


}

