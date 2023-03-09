import java.util.Random;

public class RoboTicTacToe {
    Character[][] TicTacToeGameSpace;
    int[] WinningLocation;
    Character Winner;
    public static void main(String[] args) {
        for(int c=0; c<Integer.parseInt(args[0]); c++) {
            RoboTicTacToe MyGame = new RoboTicTacToe();
            while(MyGame.isNotGameOver()) {
                MyGame.PlaceX();
                MyGame.PlaceO();
            }
            MyGame.print();
        }
    }

    RoboTicTacToe() {
        TicTacToeGameSpace = new Character[3][3];
    }

    void PlaceX() {
        PlaceMark('X');
    }
    void PlaceO() {
        PlaceMark('O');
    }

    void PlaceMark(Character Mark) {
        if(isGameOver()) return;
        Random MyRandom = new Random();
        boolean searching = true;
        while(searching) {
            int x = MyRandom.nextInt(3);
            int y = MyRandom.nextInt(3);
            if(TicTacToeGameSpace[y][x] == null) {
                searching = false;
                TicTacToeGameSpace[y][x] = Mark;
            }
        }
    }

    void print() {
        StringBuilder content = new StringBuilder();
        int count = 0;
        for(Character[] row: TicTacToeGameSpace) {
            int c = 0;
            for(Character mark:row) {
                if(mark == null){
                    content.append(' ').append(' ').append(' ');
                } else {
                    content.append(' ').append(mark).append(' ');
                }
                if(c<2) content.append((char)9475);
                c++;
            }
            content.append("\n");
            if(count<2) content
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9547)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9547)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append((char) 9473)
                    .append("\n");
            count++;
        }
        if(Winner != null) {
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
            content.append("\nA STRANGE GAME.\nTHE ONLY WINNING MOVE IS\nNOT TO PLAY.\n");
        }
        System.out.println(content);
    }

    boolean isNotGameOver() {
        return isWinner() == null && !isFull();
    }

    boolean isGameOver() {
        return !isNotGameOver();
    }

    boolean isFull() {
        for(int c=0; c<3; c++) {
            for(int count=0; count<3; count++) {
                if(TicTacToeGameSpace[c][count] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    Character isWinner() {
        for(int c=0; c<3; c++) {
            if(
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

            if(
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
        if(
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
        if(
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
