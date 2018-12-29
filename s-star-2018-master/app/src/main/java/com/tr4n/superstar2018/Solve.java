package com.tr4n.superstar2018;

import android.widget.Toast;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by MyPC on 21/01/2018.
 */

public class Solve extends MainGame {

    public int Answer = 999;
    public int[] Xbox = new int[20];
    public int[] Ybox = new int[20];
    public int[] Result = new int[20];

    public int NumberOfObjects;
    private int MinimumDistance = Integer.MAX_VALUE;
    private int distance = 0;
    private boolean[] Used = new boolean[20];
    private final int BEFORE_BACKTRACKING = 0, AFTER_BACKTRACKING = 1;
    private boolean[] removeStack = new boolean[1000];
    private PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
    private Stack<Integer> StackFirst = new Stack<Integer>();
    private Stack<Integer> StackSecond = new Stack<Integer>();


    public Solve(int[][] Board) {
        Xbox[0] = PositionCharacterX;
        Ybox[0] = PositionCharacterY;
        int CountingStar = 0;
        for (int i = 1; i <= WIDTH_OF_MAP; i++) {
            for (int j = 1; j <= HEIGHT_OF_MAP; j++) {
                if (Board[i][j] == COIN_BOX) {
                    CountingStar++;
                    Xbox[CountingStar] = i;
                    Ybox[CountingStar] = j;
                }
            }
        }
        this.NumberOfObjects = CountingStar;
        this.Answer = 0;
        for (int _position = 1; _position <= NumberOfObjects; _position++) {
            this.Answer += Distance(_position - 1, _position);
        }

        Initialization();
        BackTrack(1);

    }

    public int[] getResult(){
        return Result;
    }

    public int[] getXbox(){
        return Xbox;
    }
    public int[] getYbox(){
        return Ybox;
    }

    private void Initialization() {
        Result[0] = 0;
        Arrays.fill(Used, false);
        Arrays.fill(removeStack, false);
        MinimumDistance = Integer.MAX_VALUE;

        for (int first = 0; first < NumberOfObjects; first++) {
            for (int second = first + 1; second <= NumberOfObjects; second++) {
                MinimumDistance = Math.min(MinimumDistance, Distance(first, second));
                priorityQueue.add(Distance(first, second));
            }
        }

        while (!priorityQueue.isEmpty()) {
            StackSecond.add(priorityQueue.remove());
        }
        while (!StackSecond.isEmpty()) {
            StackFirst.add(StackSecond.pop());
        }

    }


    private int Distance(int first, int second) {
        int Xmin = Math.min(Math.abs(Xbox[first] - Xbox[second]), WIDTH_OF_MAP - Math.abs(Xbox[first] - Xbox[second]));
        int Ymin = Math.min(Math.abs(Ybox[first] - Ybox[second]), HEIGHT_OF_MAP - Math.abs(Ybox[first] - Ybox[second]));
        return Xmin + Ymin;
    }

    private void ShowResult() {

        if (distance < this.Answer) this.Answer = distance;
    }

    private boolean BacktrackingCondition(int position, int i) {
        if (Used[i]) return false;
        if (distance + Distance(Result[position - 1], i) + (NumberOfObjects - position) * StackFirst.peek() >= this.Answer)
            return false;

        return true;
    }

    private void Decrease(int position, int i, int mode) {
        int edge = Distance(Result[position - 1], i);
        if (mode == BEFORE_BACKTRACKING) {
            Used[i] = true;
            distance += edge;
            if (edge == StackFirst.peek()) {
                StackSecond.add(StackFirst.pop());
                removeStack[position] = true;
            }

        } else if (mode == AFTER_BACKTRACKING) {
            Used[i] = false;
            distance -= edge;
            if (removeStack[position]) {
                StackFirst.add(StackSecond.pop());
                removeStack[position] = false;
            }
        }
    }

    private void BackTrack(int position) {
        if (position > NumberOfObjects) {
            ShowResult();
            return;
        }

        for (int i = 1; i <= NumberOfObjects; i++) {
            if (BacktrackingCondition(position, i)) {
                Decrease(position, i, BEFORE_BACKTRACKING);
                Result[position] = i;
                BackTrack(position + 1);
                Decrease(position, i, AFTER_BACKTRACKING);
            }
        }
    }


}
