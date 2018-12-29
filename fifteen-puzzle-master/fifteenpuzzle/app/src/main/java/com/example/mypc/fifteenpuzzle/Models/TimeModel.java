package com.example.mypc.fifteenpuzzle.Models;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * Created by MyPC on 05/04/2018.
 */

public class TimeModel {
    public Chronometer chronometer;
    public long timeWhenStopped;
    public boolean started;
    public boolean isPausing;


    public TimeModel(Chronometer chronometer, long timeWhenStopped) {
        this.chronometer = chronometer;
        this.timeWhenStopped = timeWhenStopped;
        started = false;
        isPausing = false;
    }

    public void Start() {
        chronometer.start();
        timeWhenStopped = 0;
        isPausing = false;
        started = true;
    }

    public void Pause() {
        if(this.isPausing) return;
        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.setBase(SystemClock.elapsedRealtime() +timeWhenStopped);
        chronometer.stop();
        isPausing = true;
    }

    public void Continue() {
        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();
        isPausing = false;
    }

    public void Stop() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        started = false;
        isPausing = false;
    }

    public void Reset() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        started = true;
        isPausing = false;
    }

}
