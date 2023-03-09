import java.awt.desktop.OpenFilesEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuChecker {

    public static void main(String[] args) {
        int[][] SudokuSolution = new int[9][9];
        if(args.length != 1) {
            System.out.println("Please specify file name for sudoku solution.");
            System.exit(1);
        }
        Scanner SudokuFileScanner = OpenFile(args[0]);
        int lineCount = 0;
        while(SudokuFileScanner.hasNextLine()) {
            SudokuSolution[lineCount] = SpacedIntsToArray(SudokuFileScanner.nextLine());
            lineCount++;
        }

        int[][][] MiniGrids = new int[9][3][3];
        for(int SubArrayCount = 0; SubArrayCount < 9; SubArrayCount++ ){
            for(int y=0; y<3; y++) {
                for(int x=0; x<3; x++) {
                    int suby = y + ((SubArrayCount / 3) * 3);
                    int subx = x + ((SubArrayCount % 3) * 3);
                    MiniGrids[SubArrayCount][y][x] = SudokuSolution[suby][subx];
                }
            }
        }

        boolean valid = true;

        for(int c1=0; c1<9; c1++) {
            boolean[] foundRow = new boolean[9];
            boolean[] foundColumn = new boolean[9];
            for(int c2=0; c2<9; c2++) {
                int rowValueIndex = SudokuSolution[c1][c2] - 1;
                int columnValueIndex = SudokuSolution[c2][c1] - 1;
                if(foundRow[rowValueIndex]) {
                    valid = false;
                } else {
                    foundRow[rowValueIndex] = true;
                    foundColumn[columnValueIndex] = true;
                }
            }
        }

        for(int[][] Grid: MiniGrids) {
            boolean[] found = new boolean[9];
            for(int c1=0; c1<3; c1++) {
                for(int c2=0; c2<3; c2++) {
                    int valueIndex = Grid[c1][c2] - 1;
                    if(found[valueIndex]) {
                        valid = false;
                    } else {
                        found[valueIndex] = true;
                    }
                }
            }
        }

        System.out.println(valid);

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

    static void print(int[][] Matrix) {
        StringBuilder content = new StringBuilder();
        for(int[] row: Matrix) {
            content.append(Arrays.toString(row)).append("\n");
        }
        System.out.println(content);
    }
}
