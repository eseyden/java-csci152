/**
 * Notes
 *
 * No 16-clue Sudoku
 * https://arxiv.org/abs/1201.0749
 *
 * Deductive solving
 * https://medium.com/@eneko/solving-sudoku-puzzles-programmatically-with-logic-and-without-brute-force-b4e8b837d796
 *
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuSolver {
    static Integer[][] Puzzle;
    static int[][] Tries = new int[9][9];
    public static Integer ConvertChar(char myChar) {
        int myValue = Character.getNumericValue(myChar);
        if(myValue == -1) return null;
        return myValue;
    }
    public static void main(String[] args) {
        Puzzle = ReadSudokuPuzzle("impossible_board.txt");
        Solve();
        if(CheckPuzzle(Puzzle)) {
            System.out.println("SOLVERD");
        }
        System.out.println(getMaxValue(Tries));
        print(Tries);
        print(Puzzle);
    }

    public static boolean Solve() {
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++) {
                if(Puzzle[row][col] == null) {
                    Tries[row][col]++;
                    clearScreen();
                    System.out.println(getMaxValue(Tries));
                    print(Tries);
                    for(int number=1; number<=9; number++){
                        if(isValidMove(row,col,number)) {
                            Puzzle[row][col] = number;
                            if(Solve()) {
                                return true;
                            } else {
                                Puzzle[row][col] = null;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidMove(int row, int col, int number) {
        Integer[][] myPuzzle = new Integer[9][9];
        for(int c=0; c<9; c++){
            myPuzzle[c] = Puzzle[c].clone();
        }
        myPuzzle[row][col] = number;
        return CheckPuzzle(myPuzzle);
    }

    public static Integer[][] ReadSudokuPuzzle(String FileName){
        Integer[][] InitialBoardState = new Integer[9][9];
        Scanner SudokuFileScanner = OpenFile(FileName); // Open file for reading
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
     * Fun for checking the mini grid algo
     * @param Matrix
     */
    static void print(Integer[][] Matrix) {
        StringBuilder content = new StringBuilder();
        for(Integer[] row: Matrix) {
            content.append(Arrays.toString(row).replace("null"," ")).append("\n");
        }
        System.out.println(content);
    }

    /**
     * Fun for checking the mini grid algo
     * @param Matrix
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
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getMaxValue(int[][] my2dArray) {
        int out = 0;
        for(int[] array: my2dArray) {
            for(int val: array) {
                if(val > out) out = val;
            }
        }
        return out;
    }

    public static int countNonNullValues(Integer[][] myMatrix){
        int out = 0;
        for(Integer[] array: myMatrix) {
            for(Integer val: array) {
                if(val != null) out++;
            }
        }
        return out;

    }
}