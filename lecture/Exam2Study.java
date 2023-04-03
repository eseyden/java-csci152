import java.util.Arrays;

public class Exam2Study {
    public static void main(String[] args) {

        int[][] M = new int[5][4];
        for (int i=0; i<4; i++) {
            for (int j=0; j<5; j++)
                M[j][i] = i * j;
        }
        printArrays(M);
        M[2] = M[4];
        System.out.println();
        printArrays(M);
        System.out.println();
        System.out.println(M[2][3]); // Part One
        M[4][3] = M[2][2];
        printArrays(M);
        System.out.println();
        System.out.println(M[2][3]);
    }

    static void  printArrays(int[][] AS) {

        for(int[] A: AS){
            System.out.println(Arrays.toString(A));
        }
    }
}
