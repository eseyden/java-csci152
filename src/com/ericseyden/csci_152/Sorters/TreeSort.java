package com.ericseyden.csci_152.Sorters;

import java.util.Arrays;

public class TreeSort {
    public static void main(String[] args) {
        int[] myArray = new int[] {5,129,2,432,5,3,2,4,6,7,91,24,25};
        System.out.println(Arrays.toString(sort(myArray)));

    }

    static int[] sort(int[] toBeSorted) {
        Tree myTree = new Tree();
        for(int val: toBeSorted) {
            myTree.Insert(val);
        }
        Sorter mySorter = new Sorter(toBeSorted.length);
        mySorter.traverse(myTree.Root);
        return mySorter.out;
    }





}

class Sorter {
    int[] out;
    int index;

    Sorter(int Length) {
        out = new int[Length];
    }
    void traverse(Node myNode) {
        if(myNode == null ) return;
        traverse(myNode.Left);
        for(int c=0; c<myNode.Occurrences; c++)
            out[index++] = myNode.Value;
        traverse(myNode.Right);
    }
}

class Tree {
    Node Root;
    void Insert(int Value) {
        if(Root == null) {
            // If we don't have a root node create one.
            Root = new Node(Value);
            return;
        }
        boolean Searching = true; // Loop Control
        Node CurrentLeaf = Root;
        while(Searching) { // BEGIN WHILE
            if(CurrentLeaf.Value > Value){ // Our current node is greater than the new one.
                if(CurrentLeaf.Left == null) { // Empty node on the left
                    CurrentLeaf.Left = new Node(Value); // Create new value
                    Searching = false; // FLAG WHILE FOR TERMINATION
                } else {
                    CurrentLeaf = CurrentLeaf.Left; // Traverse the left path
                }
            } else if (CurrentLeaf.Value < Value) { // Current node is less than the new one
                if(CurrentLeaf.Right == null) { // Empty node on the right
                    CurrentLeaf.Right = new Node(Value);
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Right; // Traverse the right path
                }
            } else {
                CurrentLeaf.Occurrences++;
                Searching = false; // Node value and new value is the same, terminate
            }
        } // END WHILE
    }
}

class Node {
    Node Left;
    Node Right;
    int Value;
    int Occurrences;
    Node(int myValue) {
        Value = myValue;
        Occurrences = 1;
        Left = null;
        Right = null;
    }
}
