package com.example.mypc.a15puzzlegametechkids;


import java.util.*;

public class Node extends SupportNode implements Comparable<Node> {

    String name;
    long value;
    int[][] state= new int[10][10];
    Node parent ;
    int previousDirect;


    public Node(String name, long value) {
        this.name = name;
        this.value = value;
        this.parent = null;
        this.previousDirect = -1;

    }

    public Node(String name, long value, int previousDirect ) {
        this.name = name;
        this.value = value;
        this.parent = null;
        this.previousDirect = previousDirect;
    }

    public Node(int state[][]) {
        this.name = getHashStringState(state);
        this.value = getValueState(state);
        this.state = copyTable(state);
        parent = null;
        this.previousDirect = -1;
    }

    public Node(int state[][], int previousDirect) {
        this.name = getHashStringState(state);
        this.value = getValueState(state);
        this.state = copyTable(state);
        parent = null;
        this.previousDirect = previousDirect;
    }

    @Override
    public int compareTo(Node arg0) {
        // TODO Auto-generated method stub

        return this.value > arg0.value ?  1 : this.value == arg0.value ? 0 :  -1;
    }

    public boolean isEquals(Node arg0) {
        return (this.name.compareTo(arg0.name) == 0 && this.value == arg0.value);
    }

    public ArrayList<Node> getNodeDirrect(){

        ArrayList<Node> nodeList = new ArrayList<Node>();
        for(int dir = 0 ; dir < 4 ; dir ++ ) {
            int[][] newState = getNewState(this.state, dir);
            if(newState != null) {

                nodeList.add(new Node(newState, dir));
            }
        }

        return nodeList;

    }



}
