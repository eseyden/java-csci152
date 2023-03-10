/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #5
 * Problem 2
 * (SudokuChecker.java)
 * -------------------------------------------------------------------------------
 * This program reads a sudoku solution from a file and checks if it is valid.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-03-10
 * -------------------------------------------------------------------------------
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuChecker {

    public static void main(String[] args) {
        // Use a 10x10 array for holding the state of the Sudoku puzzle
        int[][] SudokuSolution = new int[9][9];

        //Check for file name argument
        if(args.length != 1) {
            System.out.println("Please specify file name for sudoku solution.");
            System.exit(1);
        }


        Scanner SudokuFileScanner = OpenFile(args[0]); // Open file for reading
        int lineCount = 0;
        while(SudokuFileScanner.hasNextLine()) { // read sudoku solution file into array
            SudokuSolution[lineCount] = SpacedIntsToArray(SudokuFileScanner.nextLine());
            lineCount++;
        }

        // Store puzzle into 9 3x3 matrices
        int[][][] MiniGrids = new int[9][3][3];
        // Loop through all possible 3x3 grids
        for(int SubArrayCount = 0; SubArrayCount < 9; SubArrayCount++ ){
            // Fill out a 3x3
            for(int y=0; y<3; y++) {
                for(int x=0; x<3; x++) {
                    // Figure out offset x/y to read from for each sub matrix
                    int suby = y + ((SubArrayCount / 3) * 3);
                    int subx = x + ((SubArrayCount % 3) * 3);
                    // Store the value in a sub matrix
                    MiniGrids[SubArrayCount][y][x] = SudokuSolution[suby][subx];
                }
            }
        }

        boolean valid = true; // We haven't lost yet

        // Use one loop for checking rows and columns
        for(int c1=0; c1<9; c1++) {
            // Init arrays to store if we found that value
            boolean[] foundRow = new boolean[9];
            boolean[] foundColumn = new boolean[9];
            // Loop through row/column values
            for(int c2=0; c2<9; c2++) {
                // Read value from puzzle matrix and translate it to array index
                int rowValueIndex = SudokuSolution[c1][c2] - 1;
                int columnValueIndex = SudokuSolution[c2][c1] - 1;
                // Check if value has already been indexed
                if(foundRow[rowValueIndex] || foundColumn[columnValueIndex]) {
                    valid = false; // Flag puzzle as not valid
                    break; // Puzzle invalid stop checking
                } else {
                    // Flip boolean to true for the index value of a puzzle answer for row and column
                    foundRow[rowValueIndex] = true;
                    foundColumn[columnValueIndex] = true;
                }
            }
            if(!valid) break; // Slight optimization for puzzle that gets is invalid.
        }

        // Loop through the 3x3 answer grids if puzzle hasn't been invalidated
        if(!valid) for(int[][] Grid: MiniGrids) {
            // Boolean array for values found in each mini grid
            boolean[] found = new boolean[9];
            // Loop through the 3x3
            for(int c1=0; c1<3; c1++) {
                for(int c2=0; c2<3; c2++) {
                    // Store found value
                    int valueIndex = Grid[c1][c2] - 1;
                    if(found[valueIndex]) { // We found a duplicate
                        valid = false; // Fail puzzle validation
                    } else {
                        found[valueIndex] = true; // Store true at index of puzzle value
                    }
                }
            }
        }

        if(valid) System.out.println("Valid");
        else System.out.println("Invalid");

    }

    /**
     * Open File
     * @param filename File Name
     * @return Scanner
     */
    public static Scanner OpenFile(String filename) {
        File FileHandle = new File(filename);
        try {
            return new Scanner(FileHandle);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        return null;
    }
    public static int[] SpacedIntsToArray (String line) {

        // Convert our single line into an array of strings
        String[] NumbersAsStrings = line.split(" ");

        // Initialize an int array that's the same size as our array of strings
        int[] Numbers = new int[NumbersAsStrings.length];

        // Convert each string representation into an integer and store it
        for (int i=0; i<NumbersAsStrings.length; i++)
            Numbers[i] = Integer.parseInt(NumbersAsStrings[i]);

        // Ta-da!
        return Numbers;

    }

    /**
     * Fun for checking the mini grid algo
     * @param Matrix
     */
    static void print(int[][] Matrix) {
        StringBuilder content = new StringBuilder();
        for(int[] row: Matrix) {
            content.append(Arrays.toString(row)).append("\n");
        }
        System.out.println(content);
    }
}
