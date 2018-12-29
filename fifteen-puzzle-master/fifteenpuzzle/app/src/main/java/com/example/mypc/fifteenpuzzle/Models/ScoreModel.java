package com.example.mypc.fifteenpuzzle.Models;

/**
 * Created by MyPC on 05/04/2018.
 */

public  class ScoreModel implements Comparable<ScoreModel> {
    private static final String TAG = "ScoreModel";
    public String name;
    public String time;
    public int move;

    public ScoreModel(String name, String time, int move) {
        this.name = name;
        this.time = time;
        this.move = move;
    }

    @Override
    public String toString() {
        return this == null ? null
                : this.move > 1
                ? ("   " + this.name + "\n          in " + this.time + "\n          and " + this.move + " moves  \n ---------------------------")
                : ("   " + this.name + "\n          in " + this.time + "\n          and " + this.move + " move   \n ---------------------------");
    }

    //hh:mm:ss
    public int getValue() {
        int res = 0;
        String time = this.time.substring(0);

        while (time.contains(":")) {
            int i;
            for (i = 0; i < time.length(); i++)
                if (time.charAt(i) == ':') break;
            res = res * 60 + Integer.parseInt(time.substring(0, i));
            time = time.substring(i + 1);
        }

        res = res * 60 + Integer.parseInt(time) + this.move * 10;

        return res;
    }

    public int compareTo(ScoreModel other) {
        return (this.getValue() - other.getValue());
    }
}
