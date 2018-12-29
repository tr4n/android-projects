package com.tr4n.superstar2018;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by MyPC on 21/01/2018.
 */
public class Sound {


    private MediaPlayer SystemSound;
    public MediaPlayer BackgroundSound ;
    private Context context;
    private int[] ID_OF_SOUNDS = {R.raw.backgroundone, R.raw.backgroundpiano, R.raw.backgroundtwo, R.raw.cameraclick, R.raw.cancel, R.raw.confirmationalbert,
            R.raw.falseback, R.raw.lose, R.raw.messcoin, R.raw.snapping, R.raw.stranglesympathy, R.raw.walking, R.raw.wrongselection};

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



