/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #5
 * Problem 3
 * (RobotTicTacToe.java)
 * -------------------------------------------------------------------------------
 * This program randomly plays tic-tac-toe against itself.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-03-10
 * -------------------------------------------------------------------------------
 */
import java.util.Random;

public class RoboTicTacToe {
    Character[][] TicTacToeGameSpace; // Matrix to hold state of play grid
    int[] WinningLocation; // Keep track of winning moves
    Character Winner; // Who won
    public static void main(String[] args) {

        if(args.length != 1) {
            System.out.println("Please provide the number of games to play.");
            System.exit(1);
        }

        // Main program loop, runs for as many games requested
        for(int c=0; c<Integer.parseInt(args[0]); c++) {
            RoboTicTacToe MyGame = new RoboTicTacToe(); // Instance a new class per game
            while(MyGame.isNotGameOver()) { // Keep playing until over
                MyGame.PlaceX(); // Attempt to place an X
                MyGame.PlaceO(); // Attempt to place an O
            }
            MyGame.print(); // Print game results
        }
    }

    /**
     * New game
     * Init the play field with null
     */
    RoboTicTacToe() {
        TicTacToeGameSpace = new Character[3][3];
    }

    /**
     * Place X
     * Puts an X into the play field
     */
    void PlaceX() {
        PlaceMark('X');
    }

    /**
     * Place O
     * Runs place mark routine with the O character
     */
    void PlaceO() {
        PlaceMark('O');
    }

    /**
     * Place Mark
     * Randomly places a character into the playfield.
     * @param Mark Character to play with.
     */
    void PlaceMark(Character Mark) {
        // Prevents placing a mark if the grid is full or game is over.
        if(isGameOver()) return;

        Random MyRandom = new Random(); // Init random class
        while(true) { // Keep trying random values until blank spot is found.

            // Two random numbers between 0 and 2
            int x = MyRandom.nextInt(3);
            int y = MyRandom.nextInt(3);

            if(TicTacToeGameSpace[y][x] == null) { // Check if empty
                TicTacToeGameSpace[y][x] = Mark; // Place a mark
                return; // Exit function
            }
        }
    }

    /**
     * Print state of board
     */
    void print() {
        StringBuilder content = new StringBuilder(); // Holder for output
        int count = 0; // Keep track of row count
        for(Character[] row: TicTacToeGameSpace) {
            int c = 0; // Keep track of column count
            for(Character mark:row) {
                if(mark == null){ // Print blank spot
                    content.append(' ').append(' ').append(' ');
                } else { // Print mark
                    content.append(' ').append(mark).append(' ');
                }
                if(c<2) content.append((char)9475); // Add a vertical separator
                c++; // Increment column count
            }
            content.append("\n"); // New line
            if(count<2) content // build up tic-tac-toe grid
                    .append((char) 9473) // UTF-8 screen drawing chars -
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9547) // UTF-8 drawing +
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9547) // Should have used const
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9473) // Does java have const?
                    .append("\n");
            count++; // Increment row
        }
        if(Winner != null) { // Tell the tale of who won and what the winning move was
            content.append("\n")
                    .append(Winner)
                    .append(" wins! (")
                    .append('[')
                    .append(WinningLocation[0])
                    .append(',')
                    .append(WinningLocation[1])
                    .append(']')
                    .append(',')
                    .append('[')
                    .append(WinningLocation[2])
                    .append(',')
                    .append(WinningLocation[3])
                    .append(']')
                    .append(',')
                    .append('[')
                    .append(WinningLocation[4])
                    .append(',')
                    .append(WinningLocation[5])
                    .append(']')
                    .append(')')
                    .append("\n");

        } else {
//            content.append("\nA STRANGE GAME.\nTHE ONLY WINNING MOVE IS\nNOT TO PLAY.\n");
            content.append("\nTie!\n");
        }
        System.out.println(content);
    }

    /**
     * If there is no winner
     * and the board isn't full
     * the game is not over
     * @return If the game must continue
     */
    boolean isNotGameOver() {
        return isWinner() == null && !isFull();
    }

    /**
     * Is the game over?
     * Let's check if it isn't over.
     * @return
     */
    boolean isGameOver() {
        return !isNotGameOver();
    }

    /**
     * Is Full
     * Checks the game space
     * To see if there are any open
     * Spots
     * @return If there are still free spots
     */
    boolean isFull() {
        for(int c=0; c<3; c++) { // Check rows
            for(int count=0; count<3; count++) { // Check columns
                if(TicTacToeGameSpace[c][count] == null) { // If there isn't a vale
                    return false; // Then we are not full
                }
            }
        }
        return true; // Never found a free space
    }

    /**
     * Is Winner
     * Keeps track of the winning
     * The real star of the program
     * @return Who Won
     */
    Character isWinner() {
        for(int c=0; c<3; c++) { // one loop for columns and rows
            if(     // Brute force logic check the row
                    TicTacToeGameSpace[c][0] != null &&
                            (
                                    TicTacToeGameSpace[c][0]
                                            .equals(TicTacToeGameSpace[c][1])
                                    &&
                                    TicTacToeGameSpace[c][1]
                                            .equals(TicTacToeGameSpace[c][2])
                            )
            ) {
                WinningLocation = new int[]{c,0,c,1,c,2};
                Winner = TicTacToeGameSpace[c][0];
                return Winner;
            }

            if(     //Check the column
                    TicTacToeGameSpace[0][c] != null &&
                            (
                                    TicTacToeGameSpace[0][c]
                                            .equals(TicTacToeGameSpace[1][c])
                                            &&
                                            TicTacToeGameSpace[1][c]
                                                    .equals(TicTacToeGameSpace[2][c])
                            )
            ) {
                WinningLocation = new int[]{0,c,1,c,2,c};
                Winner = TicTacToeGameSpace[0][c];
                return Winner;
            }

        }
        if(     // Check the first diagonal
                TicTacToeGameSpace[0][0] != null &&
                        (
                                TicTacToeGameSpace[0][0]
                                        .equals(TicTacToeGameSpace[1][1])
                                        &&
                                        TicTacToeGameSpace[1][1]
                                                .equals(TicTacToeGameSpace[2][2])
                        )
        ) {
            WinningLocation = new int[] {0,0,1,1,2,2};
            Winner = TicTacToeGameSpace[0][0];
            return Winner;
        }
        if(     // Check the second diagonal
                TicTacToeGameSpace[0][2] != null &&
                        (
                                TicTacToeGameSpace[0][2]
                                        .equals(TicTacToeGameSpace[1][1])
                                        &&
                                        TicTacToeGameSpace[1][1]
                                                .equals(TicTacToeGameSpace[2][0])
                        )
        ) {
            WinningLocation = new int[]{0,2,1,1,2,0};
            Winner = TicTacToeGameSpace[0][2];
            return Winner;
        }
        return null;
    }
}
