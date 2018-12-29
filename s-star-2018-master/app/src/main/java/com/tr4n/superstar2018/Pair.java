package com.tr4n.superstar2018;

/**
 * Created by MyPC on 21/01/2018.
 */

public class Pair {
    public int first;
    public int second;
    public int third ;

    public Pair(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean isEqual(Pair other) {
        return ((this.first  == other.first) && (this.second == other.second) && (this.third == other.third)) ;
    }


}
