package com.example.mypc.fifteenpuzzle.Models;

/**
 * Created by MyPC on 05/04/2018.
 */

public class SpecialPuzzleModel {
    public int x;
    public int y;
    public int value;
    public boolean isEmpty;

    public SpecialPuzzleModel(int x, int y, int value, boolean isEmpty) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.isEmpty = isEmpty;
    }
}