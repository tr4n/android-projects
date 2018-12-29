package com.example.mypc.a15puzzlegametechkids;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * Created by MyPC on 27/03/2018.
 */

public class Timer {
    public Chronometer chronometer;
    public long timeWhenStopped;
    public boolean started;
    public boolean isPausing;



    public Timer(Chronometer chronometer, long timeWhenStopped) {
        this.chronometer = chronometer;
        this.timeWhenStopped = timeWhenStopped;
        started = false;
        isPausing = false;
    }

    public void Start(){
        chronometer.start();
        timeWhenStopped = 0;
        isPausing = false;
        started = true;
    }

    public void Pause(){
        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.stop();
        isPausing = true;
    }

    public void Continue(){
        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();
    }

    public void Stop(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        started = false;
        isPausing = false;
    }

    public void Reset(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        timeWhenStopped = 0;
        started = true;
        isPausing = false;
    }

}
