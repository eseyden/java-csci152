/**
 * ------------------------
 * CSCI 152, Final Project
 * (SudokuSolver.java)
 * ------------------------
 * Eric Seyden
 * 2023-05-08
 * ------------------------
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuSolver {
    static Integer[][] Puzzle; // Working Puzzle Matrix
    static int[][] Tries = new int[9][9]; // Tallies backtracks

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Please provide file name of Sudoku puzzle.");
            System.exit(1);
        }
        String filename = args[0];
        // Parse Sudoku Puzzle Initial State
        Puzzle = ReadSudokuPuzzle(filename);
        // Try catch for impossible puzzles.
        try {
            Solve(); // Run main recursive function
        } catch (ImpossiblePuzzleException e) {
            System.out.println(e); //Output problem statement
        }

        print(Puzzle); // Output puzzle state
        if(CheckPuzzle(Puzzle)) {
            System.out.println("Solved");
        } else {
            System.out.println("Unsolvable by this algorithm.");
        }
    }

    /**
     * Solve
     * ----------------
     * Loops through entire puzzle until solved
     * Will throw an error if any cell has 100k+ tries
     * @return Puzzle solving state
     */
    public static boolean Solve() {
        // Loop through all cells of puzzle
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++) {
                // Check for blank cells
                if(Puzzle[row][col] == null) {
                    Tries[row][col]++; // Increment try counter
                    if(getMaxValue(Tries) > 100000) { // We tried too hard, this algo probably won't work.
                        throw new ImpossiblePuzzleException("Impossible puzzle, over 100k backtracks.");
                    }
                    // Loop through possible cell values
                    for(int number=1; number<=9; number++){
                        // Was that move OK?
                        if(isValidMove(row,col,number)) {
                            // Place guess in puzzle matrix
                            Puzzle[row][col] = number;
                            // Try to solve the next blank space.
                            if(Solve()) {
                                //Bubble up the solution state out of recursion.
                                return true;
                            } else {
                                // That value didn't work
                                Puzzle[row][col] = null;
                            }
                        }
                    }
                    /*
                     None of the numbers work as a solution
                     This will trigger backtracking
                     */
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Is Valid Move
     * ------------------------------
     * We don't want to place invalid moves on the grid
     * This function checks moves before they are placed.
     * @param row Row we are placing a value at
     * @param col Column we are placing a value at
     * @param number Value we are placing
     * @return Is it a valid move?
     */
    public static boolean isValidMove(int row, int col, int number) {
        Integer[][] myPuzzle = new Integer[9][9]; // Create a new puzzle state matrix
        for(int c=0; c<9; c++){ // Copy the rows
            myPuzzle[c] = Puzzle[c].clone();
        }
        // Place the value in the temp puzzle state
        myPuzzle[row][col] = number;
        return CheckPuzzle(myPuzzle); // Return puzzle validity
    }

    /**
     * Read Sudoku Puzzle
     * -----------------------
     * Parses a text representation of a Sudoku puzzle into a 9x9 integer matrix.
     * @param FileName File to read
     * @return 9x9 integer matrix
     */
    public static Integer[][] ReadSudokuPuzzle(String FileName){
        Integer[][] InitialBoardState = new Integer[9][9];
        Scanner SudokuFileScanner = OpenFile(FileName); // Open file for reading
        // Read all lines of input
        String Line1 = SudokuFileScanner.nextLine();
        String Line2 = SudokuFileScanner.nextLine();
        String Line3 = SudokuFileScanner.nextLine();
        String Line4 = SudokuFileScanner.nextLine();
        String Line5 = SudokuFileScanner.nextLine();
        String Line6 = SudokuFileScanner.nextLine();
        String Line7 = SudokuFileScanner.nextLine();
        String Line8 = SudokuFileScanner.nextLine();
        String Line9 = SudokuFileScanner.nextLine();
        String Line10 = SudokuFileScanner.nextLine();
        String Line11 = SudokuFileScanner.nextLine();
        // Read in input lines that have puzzle values to matrix
        InitialBoardState[0] = readLine(Line1);
        InitialBoardState[1] = readLine(Line2);
        InitialBoardState[2] = readLine(Line3);
        InitialBoardState[3] = readLine(Line5);
        InitialBoardState[4] = readLine(Line6);
        InitialBoardState[5] = readLine(Line7);
        InitialBoardState[6] = readLine(Line9);
        InitialBoardState[7] = readLine(Line10);
        InitialBoardState[8] = readLine(Line11);
        return InitialBoardState;
    }

    /**
     * Convert Character
     * -------------------
     * Returns null if not a number
     * @param myChar Evaluation target
     * @return Integer value
     */
    public static Integer ConvertChar(char myChar) {
        int myValue = Character.getNumericValue(myChar);
        if(myValue == -1) return null;
        return myValue;
    }

    /**
     * Read Line
     * -------------------------
     * Read line of text based sudoku puzzle
     * Hardcoded format is easier to maintain and alter IMO
     * @param myLine Input line
     * @return Array of all 9 values in a Sudoku puzzle row
     */
    public static Integer[] readLine(String myLine) {
        Integer[] puzzleRow = new Integer[9];
        puzzleRow[0] = ConvertChar(myLine.charAt(0));
        puzzleRow[1] = ConvertChar(myLine.charAt(1));
        puzzleRow[2] = ConvertChar(myLine.charAt(2));
        puzzleRow[3] = ConvertChar(myLine.charAt(4));
        puzzleRow[4] = ConvertChar(myLine.charAt(5));
        puzzleRow[5] = ConvertChar(myLine.charAt(6));
        puzzleRow[6] = ConvertChar(myLine.charAt(8));
        puzzleRow[7] = ConvertChar(myLine.charAt(9));
        puzzleRow[8] = ConvertChar(myLine.charAt(10));
        return puzzleRow;
    }

    /**
     * Open File
     * ----------------
     * Basic file handling for scanning a text file
     * @param filename File to Read
     * @return Scanner Object init'd to file
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

    /**
     * Print
     * Prints out Sudoku matrix
     * @param Matrix Input matrix of integers
     */
    static void print(Integer[][] Matrix) {
        StringBuilder content = new StringBuilder();
        for(Integer[] row: Matrix) {
            content.append(Arrays.toString(row).replace("null"," ")).append("\n");
        }
        System.out.println(content);
    }

    /**
     * Same as other print but overridden for different type of integer.
     * @param Matrix Input matrix
     */
    static void print(int[][] Matrix) {
        StringBuilder content = new StringBuilder();
        for(int[] row: Matrix) {
            content.append(Arrays.toString(row).replace("null"," ")).append("\n");
        }
        System.out.println(content);
    }

    public static boolean CheckPuzzle(Integer[][] SudokuSolution) {
        boolean valid = true; // We haven't lost yet

        // Store puzzle into 9 3x3 matrices
        Integer[][][] MiniGrids = new Integer[9][3][3];
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


        for(int c1=0; c1<9; c1++) {
            // Init arrays to store if we found that value
            boolean[] foundRow = new boolean[9];
            // Loop through row/column values
            for(int c2=0; c2<9; c2++) {
                if(SudokuSolution[c1][c2] == null) continue;;
                int rowValueIndex = SudokuSolution[c1][c2] - 1;
                // Check if value has already been indexed
                if(foundRow[rowValueIndex]) {
                    valid = false; // Flag puzzle as not valid
                    break; // Puzzle invalid stop checking
                } else {
                    foundRow[rowValueIndex] = true;
                }
            }
            if(!valid) break; // Slight optimization for puzzle that gets is invalid.
        }
        for(int c1=0; c1<9; c1++) {
            // Init arrays to store if we found that value
            boolean[] foundColumn = new boolean[9];
            // Loop through row/column values
            for(int c2=0; c2<9; c2++) {
                if(SudokuSolution[c2][c1] == null) continue;;
                int columnValueIndex = SudokuSolution[c2][c1] - 1;
                // Check if value has already been indexed
                if(foundColumn[columnValueIndex]) {
                    valid = false; // Flag puzzle as not valid
                    break; // Puzzle invalid stop checking
                } else {
                    foundColumn[columnValueIndex] = true;
                }
            }
            if(!valid) break; // Slight optimization for puzzle that gets is invalid.
        }
        // Loop through the 3x3 answer grids if puzzle hasn't been invalidated
        if(valid) for(Integer[][] Grid: MiniGrids) {
            // Boolean array for values found in each mini grid
            boolean[] found = new boolean[9];
            // Loop through the 3x3
            for(int c1=0; c1<3; c1++) {
                for(int c2=0; c2<3; c2++) {
                    if(Grid[c1][c2] == null) continue;;
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

        return valid;
    }

    /**
     * Get Max Value
     * ------------------------------
     * Used to check if algo is stuck
     * Returns the largest value in a matrix
     * @param my2dArray Input matrix
     * @return Largest value
     */
    public static int getMaxValue(int[][] my2dArray) {
        int out = 0;
        for(int[] array: my2dArray) {
            for(int val: array) {
                if(val > out) out = val;
            }
        }
        return out;
    }

}

/**
 * Custom Exception for nice reading of main loop.
 */
class ImpossiblePuzzleException extends RuntimeException {
    String Error;
    public ImpossiblePuzzleException(String s) {
        Error = s;
    }
}