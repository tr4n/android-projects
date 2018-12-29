package com.example.mypc.a15puzzlegametechkids;


import java.util.*;

public class Solution  {

    public Stack<Integer> result = new Stack<Integer>();
    private int HEIGHT = 4;
    private int WIDTH = 4;

    public Solution(int[][] state, int HEIGHT, int WIDTH) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        Visit(new Node(state));

    }


    public  boolean Visit(Node node) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        HashSet<String> hashSet = new HashSet<String>();

        if (node.value == 0) {
            this.result = null;
            return true;
        }

        priorityQueue.add(node);
        hashSet.add(node.name);
        node.parent = null;

        while (!priorityQueue.isEmpty()) {

            if (priorityQueue.size() > 700000)
                return false;

            Node currentNode = priorityQueue.remove();

            if (currentNode.value == 0) {
                this.result = getStackResult(currentNode);
                return true;
            }

            ArrayList<Node> nodeList = currentNode.getNodeDirrect();

            for (Node subNode : nodeList) {
                // System.out.println("+++ \n" + getStringTable(subNode.state));
                if (!hashSet.contains(subNode.name)) {
                    priorityQueue.add(subNode);
                    hashSet.add(subNode.name);
                    subNode.parent = currentNode;

                }
            }

        }

        return false;

    }

    private Stack<Integer> getStackResult(Node node) {

        Stack<Integer> stackResult = new Stack<Integer>();
        if (node.previousDirect == -1)
            return null;

        Node previousNode = node;


        while (previousNode.previousDirect != -1 || previousNode.parent != null) {
           stackResult.add(previousNode.previousDirect);
            previousNode = previousNode.parent;
        }

        return stackResult;
    }

}
