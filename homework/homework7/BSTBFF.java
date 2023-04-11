/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #7
 * Problem 1
 * (BSTBFF.java)
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-04-05
 * -------------------------------------------------------------------------------
 * Problem 1 - Binary Search Tree - Best Friend Forever?
 * -------------------------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * BSTBFF
 * ------------------------------
 * Binary Search Tree
 * ------------------------------
 * Main program,
 * Reads the files,
 * Creates a tree.
 * Traces branches,
 * finds some values.
 */
public class BSTBFF {
    /**
     * MAIN
     * Create a tree of ints from first file argument.
     * Finds the ints from the second file argument.
     * Output the path taken.
     *
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        Tree myTree = new Tree(); // NEW TREE
        Scanner myScanner = OpenInputFile("tree1.txt"); // OPEN FILE
        while(myScanner.hasNext()) {
            myTree.Insert(myScanner.nextInt());  // POPULATE TREE
        }
        myScanner = OpenInputFile("search1.txt"); // OPEN FILE
        while(myScanner.hasNext()) {
            myTree.Find(myScanner.nextInt()); // SEARCH TREE
        }
        System.out.println(myTree.GetDepth());
    }

    /**
     * Open Input File
     *
     * @param filename File name to read.
     * @return Scanner object initialized w/ file.
     */
    private static Scanner OpenInputFile (String filename) {

        Scanner ScanningFriend = null;

        try {
            ScanningFriend = new Scanner(new File(filename));
        } catch (FileNotFoundException fnfe) {
            System.out.println("\n  ERROR:  Failed to open input file " + filename + "\n");
            System.exit(1);
        }

        return ScanningFriend;

    }

}

/**
 * Tree
 * ---------------------------
 * new binary tree
 * now branching left or right
 * quickly data found
 */
class Tree {
    Node Root; // STRUCTURE START

    int GetDepth(Node N) {
        int dL=0;
        if (N.Left != null){
            dL = GetDepth(N.Left);
        }
        int dR=0;
        if(N.Right != null){
            dR = GetDepth(N.Right);
        }

        return Math.max(dR,dL) + 1;
    }
    int GetDepth() {
        if(Root == null) return 0;
        return GetDepth(Root);
    }

    /**
     * Insert
     * Add value to binary tree
     * Left path for smaller values,
     * Right path for larger
     *
     * @param Value Int value to insert into tree
     */
    void Insert(Integer Value) {
        if(Root == null) {
            // If we don't have a root node create one.
            Root = new Node(Value);
            return;
        }
        boolean Searching = true; // Loop Control
        Node CurrentLeaf = Root;
        while(Searching) { // BEGIN WHILE
            int comparison = CurrentLeaf.Value.compareTo(Value); // Comparable returns -1, 0 or 1
            if(comparison > 0){ // Our current node is greater than the new one.
                if(CurrentLeaf.Left == null) { // Empty node on the left
                    CurrentLeaf.Left = new Node(Value); // Create new value
                    Searching = false; // FLAG WHILE FOR TERMINATION
                } else {
                    CurrentLeaf = CurrentLeaf.Left; // Traverse the left path
                }
            } else if (comparison < 0) { // Current node is less than the new one
                if(CurrentLeaf.Right == null) { // Empty node on the right
                    CurrentLeaf.Right = new Node(Value);
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Right; // Traverse the right path
                }
            } else Searching = false; // Node value and new value is the same, terminate
        } // END WHILE
    }

    /**
     * Find
     * Traverses binary tree.
     * Prints the path taken to find the value
     *
     * @param Value Value to search for
     */
    void Find(Integer Value) {
        System.out.print(Value + ":\t"); // Output value searched for
        StringBuilder out = new StringBuilder("*"); // Init string builder with root node
        boolean Searching = true; // LOOP CONTROL
        Node CurrentLeaf = Root; // Init traversal placeholder to root
        while(Searching) { // BEGIN WHILE
            int comparison = CurrentLeaf.Value.compareTo(Value); // Comparable works for more than just int
            if(comparison > 0){ // Left path
                if(CurrentLeaf.Left == null) { // Empty node
                    CurrentLeaf = null; // Clear traversal
                    Searching = false; // End loop
                    out = new StringBuilder("No match found"); // Clear string builder
                } else {
                    CurrentLeaf = CurrentLeaf.Left; // Traverse left path
                    out.append("L"); // Append output
                }
            } else if (comparison < 0) { // Right path
                if(CurrentLeaf.Right == null) { // Empty node
                    CurrentLeaf = null;
                    Searching = false;
                    out = new StringBuilder("No match found");
                } else {
                    CurrentLeaf = CurrentLeaf.Right; // Traverse right path
                    out.append("R");  // Append output
                }
            } else {
                Searching = false; // Value was found
            }
        }
        System.out.println(out); // Output path taken or indicate no match found
    }
}

/**
 * Node
 * Simple value object.
 * Contains left, right and integer value.
 */
class Node {
    Node Left; // Place nodes with lower values here
    Node Right; // Place nodes with higher values here
    Integer Value;
    Node(Integer myValue) {
        Value = myValue;
    }
}

