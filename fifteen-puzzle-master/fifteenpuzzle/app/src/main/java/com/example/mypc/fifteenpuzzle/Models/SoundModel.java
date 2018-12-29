package com.example.mypc.fifteenpuzzle.Models;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by MyPC on 05/04/2018.
 */

public class SoundModel {


    private MediaPlayer SystemSound;
    public MediaPlayer BackgroundSound ;
    private Context context;


    public SoundModel(Context context) {
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


    /**
     * Created by bùm on 3/30/2018.
     */


}