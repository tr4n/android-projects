package com.example.mypc.a15puzzlegametechkids;


public class SupportNode {

    public String result;
    private int HEIGHT = 4;
    private int WIDTH = 4;
    private int[] directX = { 0, -1, 0, 1 }; // LEFT, UP, RIGHT, DOWN
    private int[] directY = { 1, 0, -1, 0 };



    public boolean checkPositionXY(int X, int Y) {
        return (X >= 0 && X < HEIGHT && Y >= 0 && Y < WIDTH);
    }

    public int[][] copyTable(int[][] table) {
        if (table == null)
            return null;

        int[][] newTable = new int[10][10];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                newTable[i][j] = table[i][j];
            }
        }

        return newTable;
    }

    public int[][] getNewState(int[][] table, int dir) {

        int currentX = 0, currentY = 0;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                if (table[i][j] == 0) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
        }

        int newX = currentX - directX[dir];
        int newY = currentY - directY[dir];

        if (!checkPositionXY(newX, newY))
            return null;

        int[][] newState = copyTable(table);
        newState[currentX][currentY] = newState[newX][newY];
        newState[newX][newY] = 0;
        currentX = newX;
        currentY = newY;
        return newState;
    }

    public String getStringTable(int[][] table) {

        String string = "";
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                String formatString = String.format("%4d", table[i][j]);
                string += formatString;
            }
            string += "\n";
        }

        return string;
    }

    public String getHashStringState(int[][] state) {
        String string = "";
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                string += ("-" + String.valueOf(state[i][j]));
            }
        }

        return string;
    }

    public long getValueState(int[][] state) {

        long value = 0;
        long num = 0;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                if (state[i][j] != 0) {

                    if (state[i][j] != i * WIDTH + j) {
                        num++;
                        int line = state[i][j] / WIDTH;
                        int col = state[i][j] % WIDTH;
                        value += (Math.abs(i - line) + Math.abs(j - col));
                        if (line + col > 1)
                            value += (Math.max(Math.abs(i - line), Math.abs(j - col)));

                    }
                }

            }
        }

        value = 10 * value + num;
        return value;
    }

    public Node getNode(int[][] state) {

        return (new Node(getHashStringState(state), getValueState(state)));
    }



}

