package com.tr4n.superstar2018;

/**
 * Created by MyPC on 21/01/2018.
 */

public class TracingObject {
    public int first;  // PositionX
    public int second; // PositionY
    public int third;  // NumberMoving

    public TracingObject(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean isEqual(TracingObject other) {
        return ((this.first == other.first) && (this.second == other.second) && (this.third == other.third));
    }
}
